package com.barclays.paymentsSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barclays.paymentsSystem.entity.RegisteredBillers;

/**
 * 
 * @author Aruna
 *
 */
@Service
public interface RegisterBiller {

	public String registerBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
	
}
