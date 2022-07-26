package com.barclays.paymentssystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.repository.RegisterBillerRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RegisterBillerImpl implements RegisteredBiller {

	@Autowired
	RegisterBillerRepo registeredBillerRepo;

	@Override
	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers) {
		
		try {
			RegisteredBillers save = registeredBillerRepo.save(registeredBillers);
			return new ResponseEntity<RegisteredBillers>(save, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}

	}
	
	@Override
	public List<RegisteredBillers> getRegisteredBiller(){
		
		try {
			List<RegisteredBillers> list = registeredBillerRepo.findAll();
			return list;
		} catch (Exception e) {
			log.info("in catch block");
			return List.of(new RegisteredBillers());
		}
		
	}

	@Override
	public String removeRegisteredBiller(String id) {
		
		try {
			 int status = registeredBillerRepo.deleteBySequenceid(id);
			log.info("deleted successfuly: "+ status);
			if( status ==1 ) {
				return "deleted successfully";
			}
			return "no data with sequence Id: "+id;
		} catch (Exception e) {
			log.info("in catch block: "+e.getMessage());
			return "exception Occured";
		}
		
	}

}
