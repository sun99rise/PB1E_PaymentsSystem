package com.barclays.paymentssystem.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable

/**
 * 
 * @author Himal
 * 
 * primary key for bill entity
 * 
 */

public class PrimaryKeyForBill implements Serializable {
	
	@NotNull
	String billerCode;
	@NotNull
	String consumerNumber;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimaryKeyForBill other = (PrimaryKeyForBill) obj;
		if (billerCode == null) {
			if (other.billerCode != null)
				return false;
		} else if (!billerCode.equals(other.billerCode))
			return false;
		if (consumerNumber == null) {
			if (other.consumerNumber != null)
				return false;
		} else if (!consumerNumber.equals(other.consumerNumber))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billerCode == null) ? 0 : billerCode.hashCode());
		result = prime * result + ((consumerNumber == null) ? 0 : consumerNumber.hashCode());
		return result;
	}

}
