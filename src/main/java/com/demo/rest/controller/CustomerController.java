package com.demo.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.pojo.Customer;
import com.demo.rest.service.CustomerServiceImpl;

/*@CrossOrigin*/
@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service; // = new ServiceImpl();

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST/* , consumes = "application/json" */)
	public void addCustomer(@RequestBody Customer customer) {
		
		service.addCustomer(customer);
		
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET/* , produces = MediaType.ALL_VALUE */)
	public  /* Resources< */List<Customer>/* > */ viewAllCustomers() {
/*		logger.info("Customers in the list");
		for (Customer customer : service.viewAllCustomers()) {

			Link link = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customer.getCustomerId()))
					.withSelfRel();*/
			/* Resources resources; */
			/* customer.add(link); 

		}*/

		return service.viewAllCustomers();
	}

	@RequestMapping(value = "/customers/{start}/{count}", method = RequestMethod.GET)
	public Resources viewAllCustomersBetween(@PathVariable("start") int start, @PathVariable("count") int count) {
		/*logger.info("Customers on the page is viewed");*/
		List<Customer> tempCustomers = viewAllCustomers();
		int listLength = tempCustomers.size();
		List<Customer> customers = new ArrayList<>();

		for (int i = start - 1; i < start + count - 1; i++) {
			customers.add(tempCustomers.get(i));
		}
		Link previousLink = linkTo(
				methodOn(this.getClass()).viewAllCustomersBetween(start - count > 0 ? start - count : 1, count))
						.withRel("Previous");
		Link nextLink = linkTo(methodOn(this.getClass())
				.viewAllCustomersBetween(start + count > listLength ? listLength - count + 1 : start + count, count))
						.withRel("Next");

		return new Resources<>(customers, previousLink, nextLink);
	}

	@RequestMapping(value = "/customer/update", method = RequestMethod.PUT/* , consumes = "application/json" */)
	public void updateCustomer(int id, @RequestBody Customer customer) {
	/*	logger.info("Customer info to be updated");*/
		System.out.println(customer);
		service.updateCustomer(customer);
		/*logger.info("Customer info updated");*/
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@RequestParam(value = "customerId") int customerId) {
		service.deleteCustomer(customerId);
	/*	logger.info("Customer deleted");*/
		return new ResponseEntity<String>("Entity Deleted", HttpStatus.OK);

	}

	@RequestMapping(value = "/customer/searchById", method = RequestMethod.GET)
	public ResponseEntity<Resource<Customer>> getCustomerById(int customerId) {
		Customer cust = null;
		Resource<Customer> resource = null;

		for (Customer customer : service.viewAllCustomers()) {
			if (customer.getCustomerId() == customerId) {
				cust = (Customer) customer;
				resource = new Resource<Customer>(cust);

				// Link link = new Link("http://localhost:8080/customers");
				// ControllerLinkBuilder link =
				// ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).viewAllCustomers());
				// resource.add(link.withRel("All Customers"));

				resource.add(ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customerId))
						.withSelfRel());

				break;

			}
		}

		if (cust == null) {
		/*	logger.error("Customer not found");*/
			return new ResponseEntity<Resource<Customer>>(HttpStatus.NOT_FOUND);
		} else {
			/*logger.info("Customer by id");*/
			return new ResponseEntity<Resource<Customer>>(resource, HttpStatus.OK);
		}
	}

}
