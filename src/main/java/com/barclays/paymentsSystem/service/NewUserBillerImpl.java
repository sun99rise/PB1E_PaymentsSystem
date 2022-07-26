package com.barclays.paymentsSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.paymentsSystem.entity.AccountHolder;
import com.barclays.paymentsSystem.repository.NewUserBillerRepo;



@Service
public class NewUserBillerImpl implements NewUserBiller {
	
	@Autowired
	NewUserBillerRepo newUserBillerRepo;

	@Override
	public String addNewUserBiller(AccountHolder accountHolder) {
		// TODO Auto-generated method stub
		AccountHolder save = newUserBillerRepo.save(accountHolder);
		
		if(save!=null)
			return "success";
			else
				return "failure";
	}
	
	
	
	
	

}
