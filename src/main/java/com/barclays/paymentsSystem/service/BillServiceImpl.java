package com.barclays.paymentssystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.repository.BillRepo;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepo billRepo;
	
	@Override
	public String createBill(Bill bill) {
		
		Bill save = billRepo.save(bill);
		
		if(save!=null)
			return "success";
			else
				return "failure";
		
		
	}

}
