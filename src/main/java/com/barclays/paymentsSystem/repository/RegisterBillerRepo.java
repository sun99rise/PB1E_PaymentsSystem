package com.barclays.paymentssystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.RegisteredBillers;
/**
Connecting to route database using jpa repository class
*/
@Repository
public interface RegisterBillerRepo extends JpaRepository<RegisteredBillers, String> {

	// @Modifying
	// @Query("delete from registeredBiller where bill_sequence_id = ?1")
	int deleteBySequenceid(String id);
	
/** 
Retrieving registerbiller as true and paylimit greater than bill amount for given biller code and consumer number
*/
	
	public RegisteredBillers findByAccountNumberAndPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAutoPayTrueAndAutoPayLimitGreaterThan(int accountNumber,
			String billerCode, String consumerNumber,double billAmount);
}
