package com.demo.rest.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rest.dao.CustomerDAO;
import com.demo.rest.pojo.Customer;

@Component
public class CustomerServiceImpl implements Service {
	private static Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO dao; // = new DaoImpl();

	public CustomerServiceImpl() {

	}

	@Override
	public void addCustomer(Customer customer) {
		dao.save(customer);

	}

	@Override
	public List<Customer> viewAllCustomers() {
		return dao.findAll();
	}

	@Override
	public void updateCustomer(Customer customer) {
		dao.save(customer);

	}

	@Override
	public void deleteCustomer(int customerId) {
		dao.delete(dao.findByCustomerId(customerId));

	}

	@Override
	public Customer getCustomerById(int customerId) {
		logger.info("Customer searched");
		return dao.findByCustomerId(customerId);
	}

}
