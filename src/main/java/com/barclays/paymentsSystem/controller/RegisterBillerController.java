package com.barclays.paymentssystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.service.RegisterBiller;

@RestController
@RequestMapping("/registerBiller")
public class RegisterBillerController {

	@Autowired
	RegisterBiller registerBiller;
	
	@PostMapping("/register")
	public String addBiller(@RequestBody RegisteredBillers registeredBillers ) {
		
		
			return registerBiller.registerBiller(registeredBillers);
		
		
		
	}
	
}
