package com.barclays.paymentssystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaidBillsDTO {

	String billerCode;
//	String status;
	int accountNumber;
	
}
