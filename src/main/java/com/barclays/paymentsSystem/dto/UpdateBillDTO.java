package com.barclays.paymentssystem.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Defining the Data transfer object variables for UpdateBill

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBillDTO {

	private String billerCode;
	private String consumerNumber;
	private double amount;
	private Date dueDate;
	
}
