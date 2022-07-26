package com.barclays.paymentsSystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentsSystem.entity.Bill;
import com.barclays.paymentsSystem.entity.RegisteredBillers;


/**
 * 
 * @author Aruna
 *
 */

@Service
public interface RegisteredBiller {

	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
	
}
