package com.barclays.paymentssystem.service;

import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.Bill;

@Service
public interface BillService {

	public String createBill(Bill bill);
	
}
