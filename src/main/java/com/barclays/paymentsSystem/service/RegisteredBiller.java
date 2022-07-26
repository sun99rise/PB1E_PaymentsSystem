package com.barclays.paymentssystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.entity.RegisteredBillers;


@Service
public interface RegisteredBiller {

	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
	
}
