package com.barclays.paymentssystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentssystem.entity.*;

/**
Connecting to route database using Jpa repository 
*/

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {
	
//findByAccountNumberAndStatus : FETCHING BILL DETAILS : ACCOUNT NUMBER AND STATUS

	public List<Bill> findByAccountNumberAndStatus(int accountNumber, String status);

//findByPrimarKeyBillerCodeAndStatusAndAccountNumber:FETCHING BILL DETAILS : ACCOUNT NUMBER,BILLER CODE AND STATUS

	public List<Bill> findByPrimarKeyBillerCodeAndStatusAndAccountNumber(String billerCode, String status,
			int accountNumber);

//findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus : FETCHING BILL DETAILS : ACCOUNT NUMBER,BILLER CODE,CONSUMER NUMBER AND STATUS

	public Optional<Bill> findByPrimarKeyBillerCodeAndPrimarKeyConsumerNumberAndAccountNumberAndStatus(
			String billerCode, String consumerNumber, int accountNumber, String status);

//findByStatus :FETCHING BILL DETAILS: STATUS

	public List<Bill> findByStatus(String status);

	
	public Optional<Bill> findByPrimarKey(PrimaryKeyForBill billKey);

}
