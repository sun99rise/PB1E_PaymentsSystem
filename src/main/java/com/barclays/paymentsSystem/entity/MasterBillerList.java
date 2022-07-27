package com.barclays.paymentssystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author mir
 * 
 * create master biller list entity
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MasterBillerList {

	@Id
	String biller_code;
	String name;
	
}
