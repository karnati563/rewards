package com.rewards.customerrewards.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.customerrewards.model.CustomCustomerDetails;
import com.rewards.customerrewards.model.CustomerDetails;
import com.rewards.customerrewards.service.CalcuateRewardsService;

@RestController
@RequestMapping("/rewards")
public class CustomerRewardsController {

	@Autowired
	public CalcuateRewardsService calcuateRewardsService;

	@PostMapping("/calculateRewards")
	public ResponseEntity<List<CustomerDetails>> updateRewardPoints(@RequestBody List<CustomerDetails> request) {
		List<CustomerDetails> custDetls = new ArrayList<>();
		HttpStatus status;
		try {
			custDetls = calcuateRewardsService.calcuateRewards(request);
			status = HttpStatus.CREATED;
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<CustomerDetails>>(custDetls, status);
	}

	@GetMapping("/getMonthlyRecords")
	public ResponseEntity<List<CustomCustomerDetails>> getMonthlyRecrods() {
		List<CustomCustomerDetails> response = new ArrayList<>();
		HttpStatus status;
		try {
			response = calcuateRewardsService.getMonthlyRecords();
			status = HttpStatus.CREATED;
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<CustomCustomerDetails>>(response, status);
	}

	@GetMapping("/getQuarterlyRecords")
	public ResponseEntity<List<CustomCustomerDetails>> getQuarterlyRecrods() {
		List<CustomCustomerDetails> res = new ArrayList<>();
		HttpStatus status;
		try {
			res = calcuateRewardsService.getQuarterlyRecords();
			status = HttpStatus.CREATED;
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<CustomCustomerDetails>>(res, status);
	}

	@GetMapping("/getCustomerDetails/{customerId}")
	public ResponseEntity<List<CustomerDetails>> getCustomerDetails(
			@PathVariable(name = "customerId") String customerId) {
		List<CustomerDetails> response = new ArrayList<>();
		HttpStatus status;
		try {
			response = calcuateRewardsService.getCustomerDetails(customerId);
			status = HttpStatus.CREATED;
		} catch (Exception ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<CustomerDetails>>(response, status);
	}

}
