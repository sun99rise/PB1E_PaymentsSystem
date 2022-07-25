package com.barclays.paymentssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.RegisteredBillers;

@Repository
public interface RegisterBillerRepo extends JpaRepository<RegisteredBillers, Integer> {
		
}