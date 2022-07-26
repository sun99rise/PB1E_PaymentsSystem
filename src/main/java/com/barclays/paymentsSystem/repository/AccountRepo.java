package com.barclays.paymentssystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.AccountHolder;

@Repository
public interface AccountRepo extends JpaRepository<AccountHolder, Integer> {

}
