package com.barclays.paymentssystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.*;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {
	
	public final String PAY_BILL_STATUS = "pending";
	
	public List<Bill> findByAccountNumberAndStatus(int accountNumber, String status);
	public List<Bill> findByPrimarKeyBillerCodeAndStatusAndAccountNumber(String billerCode, String status, int accountNumber);
	
	public Optional<Bill> findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus(String billerCode, String consumerNumber, int accountNumber, String status );

	public Optional<Bill> findByStatus(String status);
	
}
