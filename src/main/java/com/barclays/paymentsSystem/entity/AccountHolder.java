package com.barclays.paymentssystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountHolder {
	
	@Id
	int accountNumber;
    String email;
	String name;
	String address;
	double currentBalance;
	

}
