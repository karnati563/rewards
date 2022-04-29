package com.rewards.customerrewards;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.rewards.customerrewards", "com.rewaards.exceptions" })
@SpringBootApplication
public class CustomerrewardsApplication {
	@Value("${server.port}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication.run(CustomerrewardsApplication.class, args);
	}

}
