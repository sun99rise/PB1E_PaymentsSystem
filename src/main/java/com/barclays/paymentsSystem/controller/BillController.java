package com.barclays.paymentssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	BillService billService;
	
	@PostMapping("/createbill")
	public String createBill(@RequestBody Bill bill) {
		
		return billService.createBill(bill);
		
	}
	
	
}
