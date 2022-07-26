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

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepo billRepo;

	@Autowired
	AccountRepo accountRepo;

	@Override
	public ResponseEntity<?> createBill(Bill bill) {

		try {
			Bill save = billRepo.save(bill);
			return new ResponseEntity<Bill>(save, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}

	}

	@Override
	public ResponseEntity<?> getBills() {

		try {
			List<Bill> allBills = billRepo.findAll();
			return new ResponseEntity<List<Bill>>(allBills, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@Override
	public ResponseEntity<?> getBillByUserAndStatus(int accountNumber, String status) {

		try {

			List<Bill> billList = billRepo.findByAccountNumberAndStatus(accountNumber, status);

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
			Optional<Bill> bill = billRepo.findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus(
					payBillDTO.getBillerCode(), payBillDTO.getConsumerNumber(), payBillDTO.getAccountNumber(),
					billRepo.PAY_BILL_STATUS);

			if (bill.isPresent()) {
				int accountNumber = bill.get().getAccountNumber();
				double billAmount = bill.get().getAmount();

				Optional<AccountHolder> userAccount = accountRepo.findById(accountNumber);

				if (userAccount.isPresent()) {
					double currentBalance = userAccount.get().getCurrentBalance();

					if (currentBalance >= billAmount) {

						bill.get().setStatus("paid");

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
		
		
		
		return null;
	}

}
