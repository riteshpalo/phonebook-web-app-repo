package com.rpcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneBookWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookWebAppApplication.class, args);
		System.out.println("spring boot main method");
	}

}
