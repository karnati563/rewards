package com.rewards.customerrewards.service;

import java.util.List;

import com.rewaards.exceptions.CustomerNotFoundException;
import com.rewards.customerrewards.model.CustomCustomerDetails;
import com.rewards.customerrewards.model.CustomerDetails;

public interface CalcuateRewardsService {

	public List<CustomerDetails> calcuateRewards(List<CustomerDetails> customerDetails) throws Exception;

	public List<CustomerDetails> getCustomerDetails(String customerId) throws CustomerNotFoundException;

	public List<CustomCustomerDetails> getMonthlyRecords() throws Exception;

	public List<CustomCustomerDetails> getQuarterlyRecords() throws Exception;
}
