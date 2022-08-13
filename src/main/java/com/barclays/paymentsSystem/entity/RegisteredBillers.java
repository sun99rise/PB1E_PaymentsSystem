package com.barclays.paymentssystem.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RegisteredBillers {
	
	
	@EmbeddedId
	private PrimarKey primarKey;
	
	@NotNull
	private String sequenceid=UUID.randomUUID().toString().substring(0, 8);
	
	@NotNull
	int accountNumber;
	boolean autoPay;
	@NotNull
	double autoPayLimit;
	
	
	
	@Transient
	@ManyToOne
	@JoinColumn(name = "account_number", referencedColumnName = "account_number", insertable = false, updatable = false)
	private AccountHolder accHolder11;
	
	

}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
class PrimarKey implements Serializable {
	
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
		PrimarKey other = (PrimarKey) obj;
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
