package com.rewards.customerrewards.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rewaards.exceptions.CustomerNotFoundException;
import com.rewards.customerrewards.model.CustomCustomerDetails;
import com.rewards.customerrewards.model.CustomerDetails;

@Repository
public class CustomerRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	class CustomerDetailsRowMapper implements RowMapper<CustomerDetails> {
		@Override
		public CustomerDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerDetails customer = new CustomerDetails();
			customer.setCustomerId(rs.getString("customer_id"));
			customer.setPurchaseDate(rs.getDate("purchase_date"));
			customer.setPurhcaseAmout(rs.getDouble("purchase_amount"));
			customer.setRwdsErndForThisPurchase(rs.getInt("rwds_earned_for_this_purchase"));
			return customer;
		}

	}

	class CustomCustomerDetailsRowMapper implements RowMapper<CustomCustomerDetails> {
		@Override
		public CustomCustomerDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomCustomerDetails customer = new CustomCustomerDetails();
			customer.setCustomerId(rs.getString("customer_id"));
			customer.setRwdsErndForThisPurchase(rs.getInt("total_rewards"));
			return customer;
		}

	}

	public List<CustomerDetails> findById(String id) throws CustomerNotFoundException {
		return jdbcTemplate.query("SELECT * FROM CustomerDetails WHERE CUSTOMER_ID = '" + id + "'",
				new CustomerDetailsRowMapper());
	}

	public CustomerDetails savaCustomerDetails(CustomerDetails customerDetails) throws Exception {

		String insertQuery = "insert into CustomerDetails (customer_id,purchase_date,purchase_amount,rwds_earned_for_this_purchase) values(:customer_id,:purchase_date,:purchase_amount,:rwds_earned_for_this_purchase)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		parameters.addValue("customer_id", customerDetails.getCustomerId());
		parameters.addValue("purchase_date", customerDetails.getPurchaseDate());
		parameters.addValue("purchase_amount", customerDetails.getPurhcaseAmout());
		parameters.addValue("rwds_earned_for_this_purchase", customerDetails.getRwdsErndForThisPurchase());

		namedParameterJdbcTemplate.update(insertQuery, parameters);
		return customerDetails;
	}

	public List<CustomCustomerDetails> getMontlyRecords() throws Exception {

		String insertQuery = "SELECT CUSTOMER_ID,SUM(rwds_earned_for_this_purchase) as total_rewards from customerdetails where purchase_date > DATEADD('DAY',-30, CURRENT_DATE) group by customer_id";

		return jdbcTemplate.query(insertQuery, new CustomCustomerDetailsRowMapper());
	}

	public List<CustomCustomerDetails> getQuarterlyRecords() throws Exception {

		String insertQuery = "SELECT CUSTOMER_ID,SUM(rwds_earned_for_this_purchase) as total_rewards from customerdetails where purchase_date > DATEADD('DAY',-90, CURRENT_DATE) group by customer_id";

		return jdbcTemplate.query(insertQuery, new CustomCustomerDetailsRowMapper());
	}

}
