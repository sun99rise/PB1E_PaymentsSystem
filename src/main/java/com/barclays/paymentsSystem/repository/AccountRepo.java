package com.barclays.paymentssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.AccountHolder;

//Connecting to Database using JpaRepository class

@Repository
public interface AccountRepo extends JpaRepository<AccountHolder, Integer> {

}
