package com.rewards.customerrewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomCustomerDetails {
	private String customerId;
	private int rwdsErndForThisPurchase;

}
