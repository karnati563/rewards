package com.rewards.customerrewards.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rewaards.exceptions.CustomerNotFoundException;
import com.rewards.customerrewards.dao.CustomerRepository;
import com.rewards.customerrewards.model.CustomCustomerDetails;
import com.rewards.customerrewards.model.CustomerDetails;
import com.rewards.customerrewards.service.CalcuateRewardsService;

@Service
public class CalcuateRewardsServiceImpl implements CalcuateRewardsService {

	@Autowired
	CustomerRepository customerRepository;
	@Value("${customer.basepoints}")
	private int baseRewards;

	@Value("${customer.baseamount}")
	private int baseAmount;

	@Override
	public List<CustomerDetails> calcuateRewards(List<CustomerDetails> customerDetails) throws Exception {

		for (CustomerDetails custDtls : customerDetails) {
			int rewards = (int) (custDtls.getPurhcaseAmout() / baseAmount) * baseRewards;
			custDtls.setRwdsErndForThisPurchase(rewards);
			customerRepository.savaCustomerDetails(custDtls);
		}
		return customerDetails;
	}

	@Override
	public List<CustomerDetails> getCustomerDetails(String customerId) throws CustomerNotFoundException {
		List<CustomerDetails> temp = new ArrayList<>();
		temp.addAll(customerRepository.findById(customerId));
		return temp;
	}

	@Override
	public List<CustomCustomerDetails> getMonthlyRecords() throws Exception {
		return customerRepository.getMontlyRecords();
	}

	@Override
	public List<CustomCustomerDetails> getQuarterlyRecords() throws Exception {
		return customerRepository.getQuarterlyRecords();
	}

}
