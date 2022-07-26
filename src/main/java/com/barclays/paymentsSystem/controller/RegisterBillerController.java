package com.barclays.paymentssystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.service.RegisterBiller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/registerBiller")
public class RegisterBillerController {

	@Autowired
	RegisterBiller registerBiller;

	@PostMapping("/register")
	public String addBiller(@RequestBody RegisteredBillers registeredBillers) {

		return registerBiller.registerBiller(registeredBillers);

	}

	@GetMapping("/getBillers")
	public ResponseEntity<?> getRegisteredBillers() {

		List<RegisteredBillers> billerList = registerBiller.getRegisteredBiller();

		log.info(billerList + " " + billerList.isEmpty() + " ");

		if (!billerList.isEmpty() && (billerList.get(0).getPrimarKey() == null)) {
			return new ResponseEntity<String>("Exception Occured", HttpStatus.EXPECTATION_FAILED);
		}

		if (billerList.isEmpty()) {
			return new ResponseEntity<String>("No Registered Biller", HttpStatus.OK);
		}

		return new ResponseEntity<List<RegisteredBillers>>(billerList, HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteRegisteredBiller/{bill_sequence_id}")
	public ResponseEntity<?> removeBiller(@PathVariable String bill_sequence_id){
		
		String deleteStatus = registerBiller.removeRegisteredBiller(bill_sequence_id);
		
		if(deleteStatus.contains("success"))
		return new ResponseEntity<String>(deleteStatus, HttpStatus.OK);
		else{
			return new ResponseEntity<String>(deleteStatus, HttpStatus.EXPECTATION_FAILED);
		}
		
	}

}
