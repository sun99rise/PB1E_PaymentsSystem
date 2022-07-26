package com.barclays.paymentsSystem.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.repository.RegisterBillerRepo;

public class RegisteredBiller {

	@MockBean
	RegisterBillerRepo registerBillerRepo;
	
	@Autowired
	RegisteredBiller registeredBiller;
	
	RegisteredBillers registeredBillers;
	
	@BeforeEach
	public void initialization() {
		
		//List<RegisteredBillers> billers = Stream.of(new RegisteredBillers(null, null, 0, false, 0, null, null))
		
	}
	
	@Test
	public void createRegisteredBillerTest() {
		
		//when(registerBillerRepo.save(null))
		
	}
}
