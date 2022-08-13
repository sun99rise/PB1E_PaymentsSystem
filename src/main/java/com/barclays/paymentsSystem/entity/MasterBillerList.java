package com.barclays.paymentssystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MasterBillerList {

	@Id
	private String billerCode;
	private String name;
	
}
