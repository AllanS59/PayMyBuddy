package com.PayMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PayMyBuddy.DTO.MyTransactionDTO;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class MyTransactionDTOServiceTest {

	@Autowired
	private MyTransactionDTOService transactionDTOService;
	
	@Test
	public void getTransactionsDTOTest() {
		List<MyTransactionDTO> transactionsDTO = transactionDTOService.getTransactionsDTO(6);
		MyTransactionDTO[] transactionsDTOArray = transactionsDTO.toArray(new MyTransactionDTO[0]);
		assertEquals("Marie", transactionsDTOArray[0].getConnection());
		assertEquals("-20.0€", transactionsDTOArray[1].getAmount());
		
	}

}
