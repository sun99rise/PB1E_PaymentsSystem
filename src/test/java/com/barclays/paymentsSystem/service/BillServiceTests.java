package com.barclays.paymentsSystem.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.paymentssystem.entity.Bill;
import com.barclays.paymentssystem.entity.PrimaryKeyForBill;
import com.barclays.paymentssystem.repository.BillRepo;
import com.barclays.paymentssystem.service.BillService;

@SpringBootTest
public class BillServiceTests {
	
	@Autowired
	BillService billService;
	@MockBean
	BillRepo billRepo;

	static Bill bill;

	static List<Bill> pendingBillList;
	static List<Bill> paidBillList;
	private List<Bill> list;

	@BeforeAll
	public static void getServiceObject() {

		pendingBillList = Stream.of(
				new Bill("fsfdfsdf", new PrimaryKeyForBill("b1", "789878"), 540, new Date(2022, 02, 18), "pending",
						3456706, null, null),
				new Bill("74ht6fr", new PrimaryKeyForBill("b2", "785439878"), 440, new Date(2020, 02, 13), "pending",
						345543434, null, null),
				new Bill("f8g5r65", new PrimaryKeyForBill("b3", "46575436"), 210, new Date(2022, 13, 28), "pending",
						3456706, null, null))
				.collect(Collectors.toList());
		
		
		paidBillList = Stream.of(
				new Bill("fsfdfsdf", new PrimaryKeyForBill("b1", "789878"), 540, new Date(2022, 02, 18), "paid",
						3456706, null, null),
				new Bill("74ht6fr", new PrimaryKeyForBill("b2", "785439878"), 440, new Date(2020, 02, 13), "paid",
						345543434, null, null),
				new Bill("f8g5r65", new PrimaryKeyForBill("b3", "46575436"), 210, new Date(2022, 13, 28), "paid",
						3456706, null, null))
				.collect(Collectors.toList());

	}

	@BeforeEach
	public void getBillObject() {

		bill = new Bill();

		bill.setAccountNumber(12345678);
		bill.setAmount(120);
		bill.setBil_sequence_id("fjdhfy45");
		//setDueDate(Date)
		bill.setDueDate(new Date(2022, 02, 18));
		bill.setStatus("pending");
		bill.setPrimarKey(new PrimaryKeyForBill("b1", "8476475898"));

	}

	@Test
	public void createBillTest1() {

 ResponseEntity response = new ResponseEntity<Bill>(bill, HttpStatus.OK);

		when(billRepo.save(bill)).thenReturn(null);
		assertNotEquals(bill, billService.createBill(bill).getBody());
	}

	@Test
	public void createBillTest2() {

		when(billRepo.save(bill)).thenReturn(bill);
		
		assertEquals(bill, billService.createBill(bill).getBody());
	}

	@Test
	public void getBillsTest() {

		when(billRepo.findAll()).thenReturn(pendingBillList);

		assertEquals(pendingBillList, billService.getBills().getBody());

	}

	@Test
	public void paidBillsTest() {
		
	when(billRepo.findByAccountNumberAndStatus(345543434, "paid")).thenReturn(paidBillList);
	
		assertEquals( paidBillList, billService.getBillByUserAndStatus(345543434, "paid").getBody());

	}
	
	@Test
	public void paBill() {
		
		
		
	}

}
