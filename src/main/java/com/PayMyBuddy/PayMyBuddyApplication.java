package com.PayMyBuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.PayMyBuddy.configuration.ExampleSQLDataGenerator;


@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private ExampleSQLDataGenerator sqlDataGenerator;
	
	
	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		sqlDataGenerator.createExampleSQLData();
		
	}

}

