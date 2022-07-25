package com.barclays.paymentssystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class RegisteredBillers {
	
	@Id
	int bill_sequence_id;
	
	String BillerCode;
	String consumerNumber;
	int accountNumber;
	boolean autoPay;
	double autoPayLimit;

}
