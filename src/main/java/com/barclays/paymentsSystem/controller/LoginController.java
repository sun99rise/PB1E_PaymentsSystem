package com.barclays.paymentssystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentssystem.entity.AccountHolder;


@RestController
@RequestMapping("/login")
public class LoginController {
	
//	@GetMapping("/user")
//	public ResponseEntity<String> userLogin(@RequestBody AccountHolder accountHolder ) {
//		
//		
//		return ResponseEntity<String>;
//	}
	
	@GetMapping("/manager")
	public void managerLogin(@RequestBody AccountHolder accountHolder ) {
		
	}

}
