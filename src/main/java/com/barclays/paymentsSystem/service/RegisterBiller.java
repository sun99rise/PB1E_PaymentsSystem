package com.barclays.paymentssystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.RegisteredBillers;


@Service
public interface RegisterBiller {

	public String registerBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
	
}
