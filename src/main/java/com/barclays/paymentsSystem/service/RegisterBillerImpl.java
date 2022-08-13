package com.barclays.paymentssystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.paymentssystem.constants.ServiceConstants;
import com.barclays.paymentssystem.entity.RegisteredBillers;
import com.barclays.paymentssystem.repository.RegisterBillerRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class RegisterBillerImpl implements BillerService {

	@Autowired
	RegisterBillerRepo registeredBillerRepo;

	@Override
	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers) {
		
		try {
			log.info("start - saving the biller to list");
			RegisteredBillers save = registeredBillerRepo.save(registeredBillers);
			log.info("end - biller saved: ");
			return new ResponseEntity<RegisteredBillers>(save, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(ServiceConstants.GENERAL_EXCEPTION_MESSAGE, HttpStatus.BAD_GATEWAY);

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
