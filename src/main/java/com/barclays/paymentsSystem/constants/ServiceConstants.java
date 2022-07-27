package com.barclays.paymentssystem.constants;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ServiceConstants {

	public static final String PAID_STATUS = "paid";
	public static final String PENDING_STATUS = "pending";
	public static final String RETRIVAL_EXCEPTION_MESSAGE = "Exception occured while retriving bills";
	public static final String PAID_BILLS_EXCEPTION_MESSAGE = "Exception occured while retriving paid bills";
	public static final String CREATE_BILL_EXCEPTION_MESSAGE = "Exception occured while creating bill";
	
	// manual bill pay constants
	public static final String BILL_PAID_MESSAGE = "Bill Paid Successfully";
	public static final String BILL_PAY_EXCEPTION_MESSAGE = "Exception Occured while Paying Bill"; 
	public static final String LOW_BALANCE_MESSAGE = "Unsuficient Balance for bill Payment"; 
	public static final String NO_USER_MESSAGE = "No user with given account";
	public static final String NO_PENDING_BILL_MESSAGE = "No pending bill with given details";
	
	// auto pay constants
	public static final String BILL_INELIGIBLE_MESSAGE = " Bill is not eligible for auto pay";
	public static final String GENERAL_EXCEPTION_MESSAGE = "Exception Occurred";

	

}
