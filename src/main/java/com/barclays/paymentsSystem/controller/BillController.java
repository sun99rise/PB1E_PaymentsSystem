package com.barclays.paymentssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentssystem.dto.AccountAndStatusDTO;
import com.barclays.paymentssystem.dto.GetPaidBillsDTO;
import com.barclays.paymentssystem.dto.PayBillDTO;
import com.barclays.paymentssystem.dto.UpdateBillDTO;
import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.service.BillService;

/**
 * Controller for bill related endpoints
 * @author hp
 *
 */

@RestController
@RequestMapping("/api")
public class BillController {

	@Autowired
	BillService billService;

	@PostMapping("/bill")
	public ResponseEntity<?> createBill(@RequestBody Bill bill) {

		return billService.createBill(bill);

	}
	
	@GetMapping("/allBills")
	public ResponseEntity<?> getBills() {

		return billService.getBills();

	}
	
	@PostMapping("/billByAccountAndStatus")
	public ResponseEntity<?> getBillsByAccountAndStatus(@RequestBody AccountAndStatusDTO accountAndStatusDTO) {

		return billService.getBillByUserAndStatus(accountAndStatusDTO.getAccountNumber(), accountAndStatusDTO.getStatus());

	}
	
	@PostMapping("/paidBills")
	public ResponseEntity<?> getPaidBills(@RequestBody GetPaidBillsDTO paidBillsDTO) {

		return billService.getPaidBills(paidBillsDTO);

	}
	
	@PostMapping("/payBill")
	public ResponseEntity<?> payBill(@RequestBody PayBillDTO payBillDTO) {

		return billService.manualBillPay(payBillDTO);

	}
	
	@GetMapping("/autoPayBill")
	public ResponseEntity<?> payBill() {

		return billService.autoBillPayment();

	}
	
	@PutMapping("/updateBill")
	public ResponseEntity<?> updateBill(@RequestBody UpdateBillDTO updateBillDTO) {

		return billService.updateBill(updateBillDTO);

	}

}
