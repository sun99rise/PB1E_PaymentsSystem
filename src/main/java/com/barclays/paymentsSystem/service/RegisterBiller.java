package com.barclays.paymentssystem.service;

import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.RegisteredBillers;


@Service
public interface RegisterBiller {

	public String registerBiller(RegisteredBillers registeredBillers );
	
	
}
