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


/**
 * 
 * @author Aruna
 *
 *this class will implementation all methods of registerService interface.
 * implementation includes the some crud operation and some user defined operations
 */

@Slf4j
@Service
@Transactional
public class RegisterBillerImpl implements RegisteredBiller {

	@Autowired
	RegisterBillerRepo registeredBillerRepo;
	
	/**
	 * createRegisteredBiller(RegisteredBillers registeredBillers) : method to insert the Register Biller to database table
	 * inputs: registered Biller entity - which we want to insert
	 * output: ResponseEntity<?> - will return Bill when there is happy flow, will return String if there is any exception occurred 
	 */

	@Override
	public ResponseEntity<?> createRegisteredBiller(RegisteredBillers registeredBillers) {
		
		try {
			RegisteredBillers save = registeredBillerRepo.save(registeredBillers);
			return new ResponseEntity<RegisteredBillers>(save, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

		}

	}
	
	/**
	 * getRegisteredBiller(): method to retrieve all registered bills
	 * inputs: 
	 * output: ResponseEntity<?> - will return List<Bill> when there is happy flow, will return String if there is any exception occurred 
	 */
	
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
	
	/**
	 * removeRegisteredBiller(String id): method to delete the registered bills
	 * inputs: Sequence_id
	 * output: ResponseEntity<?> - will return List<Bill> when there is happy flow, will return String if there is any exception occurred 
	 */

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
