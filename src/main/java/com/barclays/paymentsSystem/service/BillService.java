package com.barclays.paymentssystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.dto.GetPaidBillsDTO;
import com.barclays.paymentssystem.dto.PayBillDTO;
import com.barclays.paymentssystem.entity.Bill;

@Service
public interface BillService {

	public ResponseEntity<?> createBill(Bill bill);
	
	public ResponseEntity<?> getBills();
	
	public ResponseEntity<?> getBillByUserAndStatus(int accountNumber, String status);
	
	public ResponseEntity<?> getPaidBills(GetPaidBillsDTO paidBillsDTO);
	
	public ResponseEntity<?> payBill(PayBillDTO payBillDTO);
	
	public ResponseEntity<?> autoBillPayment();
	
}
