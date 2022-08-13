package com.barclays.paymentssystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.RegisteredBillers;


@Service
@Component
public interface RegisterBiller {

	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers );
	
	public List<RegisteredBillers> getRegisteredBiller(); 
	
	public String removeRegisteredBiller(String id);
	
	
}
