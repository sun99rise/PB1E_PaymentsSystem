package com.barclays.paymentssystem.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountHolder {
	
	int accountNumber;
    String email;
	String name;
	String address;
	double currentBalance;
	

}
