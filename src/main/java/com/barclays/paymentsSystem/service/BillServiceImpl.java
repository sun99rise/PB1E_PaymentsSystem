package com.barclays.paymentssystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.dto.GetPaidBillsDTO;
import com.barclays.paymentssystem.dto.PayBillDTO;
import com.barclays.paymentssystem.entity.AccountHolder;
import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.repository.AccountRepo;
import com.barclays.paymentssystem.repository.BillRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepo billRepo;

	@Autowired
	AccountRepo accountRepo;
	
	

	@Override
	public ResponseEntity<?> createBill(Bill bill) {

		try {
			
			log.info("cteating the new entr in bill table");
			Bill save = billRepo.save(bill);
			log.info("bill created successfully");
			
			log.info("returning the saved entry");
			return new ResponseEntity<Bill>(save, HttpStatus.OK);

		} catch (Exception e) {
			log.debug("in catch block: exception occured");
			return new ResponseEntity<String>("Error occured while creating bill", HttpStatus.EXPECTATION_FAILED);

		}

	}

	@Override
	public ResponseEntity<?> getBills() {

		try {
			
			log.info("retriving all bills");
			List<Bill> allBills = billRepo.findAll();			
			log.info("bills retrived successfully");
			
			log.debug("returning the bills list");
			return new ResponseEntity<List<Bill>>(allBills, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@Override
	public ResponseEntity<?> getBillByUserAndStatus(int accountNumber, String status) {

		try {

			log.info("querying bills b user account and status");
			List<Bill> billList = billRepo.findByAccountNumberAndStatus(accountNumber, status);
			log.info("bills b account and status retrived");
			
			log.info("returning the bills by given account and status");
			return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);

		}

	}

	@Override
	public ResponseEntity<?> getPaidBills(GetPaidBillsDTO billsDTO) {

		try {

			List<Bill> billList = billRepo.findByPrimarKeyBillerCodeAndStatusAndAccountNumber(billsDTO.getBillerCode(),
					"paid", billsDTO.getAccountNumber());

			return new ResponseEntity<List<Bill>>(billList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);

		}

	}

	@Override
	public ResponseEntity<?> payBill(PayBillDTO payBillDTO) {

		
		try {
			log.info("finding the bills which are in pending state for given biller and consumer number");
			Optional<Bill> bill = billRepo.findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus(
					payBillDTO.getBillerCode(), payBillDTO.getConsumerNumber(), payBillDTO.getAccountNumber(),
					billRepo.PAY_BILL_STATUS);

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
						bill.get().setStatus("paid");

						log.info("updting the current balance for user");
						double newCurrentBalance = currentBalance - billAmount;
						userAccount.get().setCurrentBalance(newCurrentBalance);
						
						
						Bill updatedBill = billRepo.save(bill.get());
						AccountHolder updatedAccount = accountRepo.save(userAccount.get());

						if (updatedBill != null && updatedAccount != null) {
							return new ResponseEntity<String>("Bill Paid", HttpStatus.OK);
						} else {
							return new ResponseEntity<String>("Problem Occured while Paying Bill", HttpStatus.OK);
						}

					}else {
						return new ResponseEntity<String>("Unsuficient Balance for bill Payment", HttpStatus.OK);
					}
				}else {
					return new ResponseEntity<String>("No user with given account", HttpStatus.OK);
				}
					
			}else {
				return new ResponseEntity<String>("No pending bill with given details", HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<?> autoBillPayment() {
		
		
		Optional<Bill> pendingBills = billRepo.findByStatus("pending");
		
		if( pendingBills.isPresent() ) {
			
			
			
		}
			
		
		return null;
	}

}
