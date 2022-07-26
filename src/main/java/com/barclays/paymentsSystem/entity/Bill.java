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


@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class PrimaryKeyForBill implements Serializable {
	
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
