package com.barclays.paymentssystem.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Bill {
	
	private String bil_sequence_id = UUID.randomUUID().toString().substring(0, 8);;
	
//	@NotNull
//	String billerCode;
//	@NotNull
//	String consumerNumber;
//	
	@EmbeddedId
	private PrimaryKeyForBill primarKey;
	
	@NotNull
	private double amount;
	@NotNull
	private Date dueDate;
	
	private String status = "pending";
	
	@NotNull
	private int accountNumber;
	
//	@Transient
//	@ManyToOne
//	@JoinColumn(name = "biller_code", referencedColumnName = "biller_code", insertable = false, updatable = false)
//	private MasterBillerList biller_code1;
	
	@Transient
	@JsonBackReference
	 @ManyToOne
	 @JoinColumn(name = "status")
	 private MasterBillerList biller;
	
//	@Transient
//	@ManyToOne
//	@JoinColumn(name = "account_number", referencedColumnName = "account_number", insertable = false, updatable = false)
//	private AccountHolder accHolder1;
	
	@Transient
	 @ManyToOne
	 @JoinColumn(name = "accountNumber")
	 private AccountHolder user;
	
	
	

}

