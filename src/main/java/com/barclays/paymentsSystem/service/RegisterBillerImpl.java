package com.barclays.paymentssystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.repository.RegisterBillerRepo;

@Service
public class RegisterBillerImpl implements RegisterBiller {

	@Autowired
	RegisterBillerRepo registeredBillerRepo;
	
	@Override
	public String registerBiller(RegisteredBillers registeredBillers) {
		// TODO Auto-generated method stub
		
		RegisteredBillers save = registeredBillerRepo.save(registeredBillers);
		
		if(save!=null)
			return "success";
			else
				return "failure";
		
	}

}
