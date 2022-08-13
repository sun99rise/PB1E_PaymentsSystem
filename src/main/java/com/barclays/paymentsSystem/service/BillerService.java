package com.barclays.paymentssystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;

import com.barclays.paymentssystem.entity.RegisteredBillers;

@Service
public interface BillerService {

public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
}
