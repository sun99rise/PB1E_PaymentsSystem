package com.barclays.paymentssystem.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

/**
 * 
 * @author Himal
 * 
 * create Bill entity
 * 
 */

public class Bill {
	
	private String bil_sequence_id = UUID.randomUUID().toString().substring(0, 8);;
	
	@EmbeddedId
	private PrimaryKeyForBill primarKey;
	
	private double amount;
	private Date dueDate;
	private String status;
	private int accountNumber;
	
	@Transient
	@ManyToOne
	@JoinColumn(name = "billerCode", referencedColumnName = "biller_code", insertable = false, updatable = false)
	private MasterBillerList biller_code1;
	
	@Transient
	@ManyToOne
	@JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber", insertable = false, updatable = false)
	private AccountHolder account_number1;
	
	
	

}


