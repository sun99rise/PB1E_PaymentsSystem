package com.barclays.paymentssystem.service;

import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.constants.ServiceConstants;
import com.barclays.paymentssystem.dto.GetPaidBillsDTO;
import com.barclays.paymentssystem.dto.PayBillDTO;
import com.barclays.paymentssystem.dto.UpdateBillDTO;
import com.barclays.paymentssystem.entity.AccountHolder;
import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.entity.PrimaryKeyForBill;
import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.repository.AccountRepo;
import com.barclays.paymentssystem.repository.BillRepo;
import com.barclays.paymentssystem.repository.RegisterBillerRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author suraj
 *
 *         this class will implementation all methods of billService interface.
 *         implementation includes the some crud operation and some user defined
 *         operations
 */

@Slf4j
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepo billRepo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	RegisterBillerRepo registerBillerRepo;

	/**
	 * createBill(Bill bill) : method to insert the new Bill to database table
	 * inputs: Bill entity - which we want to insert output: ResponseEntity<?> -
	 * will return Bill when there is happy flow, will return String if there is any
	 * exception occurred
	 */
	@Override
	public ResponseEntity<?> createBill(Bill bill) {

		try {

			log.info("creating the new entry in bill table");
			Bill save = billRepo.save(bill);
			log.info("bill created successfully");

			log.info("returning the saved entry");
			return new ResponseEntity<Bill>(save, HttpStatus.OK);

		} catch (Exception e) {
			log.debug("in catch block: exception occured");
			return new ResponseEntity<String>(ServiceConstants.CREATE_BILL_EXCEPTION_MESSAGE,
					HttpStatus.EXPECTATION_FAILED);

		}

	}

	/**
	 * getBills(): method to retrieve all bills inputs: output: ResponseEntity<?> -
	 * will return List<Bill> when there is happy flow, will return String if there
	 * is any exception occurred
	 */

	@Override
	public ResponseEntity<?> getBills() {

		try {

			log.info("retriving all bills");
			List<Bill> allBills = billRepo.findAll();
			log.info("bills retrived successfully");

			log.debug("returning the bills list");
			return new ResponseEntity<List<Bill>>(allBills, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.RETRIVAL_EXCEPTION_MESSAGE,
					HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * getBills(): method to retrieve bills by status and accountNumber inputs:
	 * accountNumber and status output: ResponseEntity<?> - will return List<Bill>
	 * when there is happy flow, will return String if there is any exception
	 * occurred
	 */

	@Override
	public ResponseEntity<?> getBillByUserAndStatus(int accountNumber, String status) {

		try {

			log.info("querying bills b user account and status");
			List<Bill> billList = billRepo.findByAccountNumberAndStatus(accountNumber, status);
			log.info("bills b account and status retrived");

			log.info("returning the bills by given account and status");
			return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.RETRIVAL_EXCEPTION_MESSAGE, HttpStatus.OK);

		}

	}

	/**
	 * getBills(): method to retrieve paid bills inputs: GetPaidBillsDTO(billerCode,
	 * "paid", accountNumber) output: ResponseEntity<?> - will return List<Bill>
	 * when there is happy flow, will return String if there is any exception
	 * occurred
	 */

	@Override
	public ResponseEntity<?> getPaidBills(GetPaidBillsDTO billsDTO) {

		try {

			List<Bill> billList = billRepo.findByPrimarKeyBillerCodeAndStatusAndAccountNumber(billsDTO.getBillerCode(),
					ServiceConstants.PAID_STATUS, billsDTO.getAccountNumber());

			return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.PAID_BILLS_EXCEPTION_MESSAGE, HttpStatus.OK);

		}

	}

	/**
	 * payBill(PayBillDTO payBillDTO): method to pay bill manually inputs:
	 * GetPaidBillsDTO(billerCode, "paid", accountNumber) output: ResponseEntity<?>
	 * - will return specific message depending on the status of transaction
	 */

	@Override
	public ResponseEntity<?> manualBillPay(PayBillDTO payBillDTO) {

		try {
			log.info("finding the bills which are in pending state for given biller and consumer number");
			Optional<Bill> bill = billRepo.findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus(
					payBillDTO.getBillerCode(), payBillDTO.getConsumerNumber(), payBillDTO.getAccountNumber(),
					ServiceConstants.PENDING_STATUS);

			log.info("pending bills retrived for given account,consume number and biller code");
			log.info("checking if pending bills list is empty");
			if (bill.isPresent()) {

				log.info("there are pending bills for given info");

				int accountNumber = bill.get().getAccountNumber();
				double billAmount = bill.get().getAmount();

				log.info("finding the user with given account number");

				Optional<AccountHolder> userAccount = accountRepo.findById(accountNumber);

				if (userAccount.isPresent()) {

					log.info("retriving the current balance for given account number");
					double currentBalance = userAccount.get().getCurrentBalance();

					log.info("checking if currentBalance >= billAmount ");
					if (currentBalance >= billAmount) {

						log.info("current balance is greater than bill amount");

						log.info("updating the status of bil to paid");
						bill.get().setStatus(ServiceConstants.PAID_STATUS);

						log.info("updting the current balance for user");
						double newCurrentBalance = currentBalance - billAmount;
						userAccount.get().setCurrentBalance(newCurrentBalance);

						Bill updatedBill = billRepo.save(bill.get());
						AccountHolder updatedAccount = accountRepo.save(userAccount.get());

						if (updatedBill != null && updatedAccount != null) {
							return new ResponseEntity<String>(ServiceConstants.BILL_PAID_MESSAGE, HttpStatus.OK);
						} else {
							return new ResponseEntity<String>(ServiceConstants.BILL_PAY_EXCEPTION_MESSAGE,
									HttpStatus.OK);
						}

					} else {
						return new ResponseEntity<String>(ServiceConstants.LOW_BALANCE_MESSAGE, HttpStatus.OK);
					}
				} else {
					return new ResponseEntity<String>(ServiceConstants.NO_USER_MESSAGE, HttpStatus.OK);
				}

			} else {
				log.info("No pending bill with given details");
				return new ResponseEntity<String>(ServiceConstants.NO_PENDING_BILL_MESSAGE, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.GENERAL_EXCEPTION_MESSAGE,
					HttpStatus.EXPECTATION_FAILED);
		}

	}

	/**
	 * autoBillPayment(): method to pay bill automatically inputs: output:
	 * ResponseEntity<?> - will return the Map<String, String> specifying billId and
	 * bill pay status in happy flow, will display the exception message depending
	 * on the status of transaction
	 */

	@Override
	public ResponseEntity<?> autoBillPayment() {

		Map<String, String> autoPayStatus = new LinkedHashMap<>();
		autoPayStatus.put("Bill_Id".toUpperCase(), "auto_bill_pay_status".toUpperCase());

		try {

			log.info("retriving all pending bills");
			List<Bill> pendingBills = billRepo.findByStatus(ServiceConstants.PENDING_STATUS);
			log.info("pending bill retrived");

			List<Bill> billsAvailableforAutoPay = new ArrayList<>();

			if (!pendingBills.isEmpty()) {

				log.info("looping on pending bills");
				for (Bill bill : pendingBills) {

					long dateDiff = getDateDiff(bill.getDueDate().toString());

					log.info("checking if bill due date is less than or equal to 3 days");
					if (dateDiff != -1 && dateDiff <= 3) {

						billsAvailableforAutoPay.add(bill);
					}
				}

				log.info("looping over bill list which are eligible for auto pay today");
				for (Bill bill : billsAvailableforAutoPay) {

					RegisteredBillers autoPayEligibleByLimit = registerBillerRepo
							.findByAccountNumberAndPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAutoPayTrueAndAutoPayLimitGreaterThan(
									bill.getAccountNumber(), bill.getPrimarKey().getBillerCode(),
									bill.getPrimarKey().getConsumerNumber(), bill.getAmount());

					if (autoPayEligibleByLimit != null) {

						ResponseEntity<?> payEntity = pay(bill);
						autoPayStatus.put(bill.getBil_sequence_id(), payEntity.getBody().toString());

					} else {
						log.debug("auto pay is false or pay limit is less than the ill amount");
						
					}

				}
				if (autoPayStatus.size() > 1)
					return new ResponseEntity<Map<String, String>>(autoPayStatus, HttpStatus.OK);
				else
					return new ResponseEntity<String>(ServiceConstants.BILL_INELIGIBLE_MESSAGE, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(ServiceConstants.NO_PENDING_BILL_MESSAGE, HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(ServiceConstants.GENERAL_EXCEPTION_MESSAGE, HttpStatus.OK);
		}

	}

	/**
	 * getDateDiff(String date): method to get difference between due date and
	 * current date for auto bill payment inputs: date in String form output: long
	 * int defining the difference in happy flow, -1 if there is any exception
	 */

	public long getDateDiff(String date) {

		LocalDate today = LocalDate.now();
		long dateDifference = -1;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date secondDate = null;
		try {
			secondDate = sdf.parse(date);
		} catch (ParseException e) {

			log.error(e.getMessage());
		}
		Date firstDate = null;
		try {
			firstDate = sdf.parse(today.toString());
		} catch (ParseException e) {

			log.error(e.getMessage());
		}

		if (firstDate != null && secondDate != null) {
			long diff = secondDate.getTime() - firstDate.getTime();
			TimeUnit time = TimeUnit.DAYS;
			long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
			dateDifference = diffrence;
			log.debug("The difference in days is : " + diffrence);

		}

		return dateDifference;
	}

	
	/**
	 * 
	 * @param bill
	 * @return ResposeEntity<String> - will return the specific according to the method logic 
	 */
	public ResponseEntity<?> pay(Bill bill) {

		try {

			int accountNumber = bill.getAccountNumber();
			double billAmount = bill.getAmount();

			log.info("finding the user with given account number");

			Optional<AccountHolder> userAccount = accountRepo.findById(accountNumber);

			if (userAccount.isPresent()) {

				log.info("retriving the current balance for given account number");
				double currentBalance = userAccount.get().getCurrentBalance();

				log.info("checking if currentBalance >= billAmount ");
				if (currentBalance >= billAmount) {

					log.info("current balance is greater than bill amount");

					log.info("updating the status of bil to paid");
					bill.setStatus(ServiceConstants.PAID_STATUS);

					log.info("updting the current balance for user");
					double newCurrentBalance = currentBalance - billAmount;
					userAccount.get().setCurrentBalance(newCurrentBalance);

					Bill updatedBill = billRepo.save(bill);
					AccountHolder updatedAccount = accountRepo.save(userAccount.get());

					if (updatedBill != null && updatedAccount != null) {
						return new ResponseEntity<String>(ServiceConstants.BILL_PAID_MESSAGE, HttpStatus.OK);
					} else {
						return new ResponseEntity<String>(ServiceConstants.BILL_PAY_EXCEPTION_MESSAGE, HttpStatus.OK);
					}

				} else {
					return new ResponseEntity<String>(ServiceConstants.LOW_BALANCE_MESSAGE, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<String>(ServiceConstants.NO_USER_MESSAGE, HttpStatus.OK);
			}

		} catch (Exception e) {
			
			return new ResponseEntity<String>(ServiceConstants.GENERAL_EXCEPTION_MESSAGE,
					HttpStatus.EXPECTATION_FAILED);

		}

	}

	/**
	 *  updateBill(UpdateBillDTO updateBillDTO)  :method to update the bill.
	 *  can be able to modify the amount and due date for the particular bill.
	 */
	
	@Override
	public ResponseEntity<?> updateBill(UpdateBillDTO updateBillDTO) {

		
		try {

			Optional<Bill> bill = billRepo.findByPrimarKey(
					new PrimaryKeyForBill(updateBillDTO.getBillerCode(), updateBillDTO.getConsumerNumber()));

			if (bill.isPresent() && bill.get().getStatus().equals(ServiceConstants.PENDING_STATUS)) {

				if (updateBillDTO.getAmount() > 0) {
					bill.get().setAmount(updateBillDTO.getAmount());
				}

				if (updateBillDTO.getDueDate() != null && updateBillDTO.getDueDate().toString().trim().length() > 0) {
					bill.get().setDueDate(updateBillDTO.getDueDate());
				}

				Bill updatedBill = billRepo.save(bill.get());

				if (updatedBill != null) {
					return new ResponseEntity<Bill>(updatedBill, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Problem Occurred while updating bill", HttpStatus.OK);
				}

			} else {

				if (bill.isPresent() && !bill.get().getStatus().equals(ServiceConstants.PENDING_STATUS))
					return new ResponseEntity<String>(
							ServiceConstants.NO_PENDING_BILLERANDCONSUMER_MESSAGE,
							HttpStatus.OK);
				else
					return new ResponseEntity<String>(ServiceConstants.NO_BILL_WITH_BILLERANDCONSUMER_MESSAGE,
							HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.GENERAL_EXCEPTION_MESSAGE, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
