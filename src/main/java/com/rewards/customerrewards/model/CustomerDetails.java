package com.rewards.customerrewards.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails {
	private String customerId;
	private double purhcaseAmout;
	private int rwdsErndForThisPurchase;
	private Date purchaseDate;

}
