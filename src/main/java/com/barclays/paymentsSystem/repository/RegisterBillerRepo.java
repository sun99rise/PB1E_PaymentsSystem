package com.barclays.paymentssystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.RegisteredBillers;

@Repository
public interface RegisterBillerRepo extends JpaRepository<RegisteredBillers, String> {

	//@Modifying
	//@Query("delete from registeredBiller where bill_sequence_id = ?1")
	  int deleteBySequenceid(String id);
}
