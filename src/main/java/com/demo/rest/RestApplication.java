package com.demo.rest;

import org.apache.logging.log4j.LogManager;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.rest.pojo.Customer;
import com.demo.rest.service.CustomerServiceImpl;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RestApplication {
	/*private static Logger logger = LoggerFactory.getLogger(RestApplication.class);*/

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
		
		/* System.out.println( "Hello World!" ); */
	}

	@Bean
	public CommandLineRunner customerRepo(CustomerServiceImpl service) {
		return (args) -> {
			service.addCustomer(new Customer(101, "Drishti Rao", 98696666));
			service.addCustomer(new Customer(102, "Nishaad M", 9872666));
			service.addCustomer(new Customer(103, "Sayali M", 981000));
			service.addCustomer(new Customer(104, "Anindya G", 9105226));
			service.addCustomer(new Customer(105, "Mehek T", 72015456));
			service.addCustomer(new Customer(106, "Lokesh U", 87642025));

			System.out.println("All customers added");
			
		/*	logger.debug("Debugging log");
			logger.info("Info log");
			logger.warn("Warning log");
			logger.error("ERROR log");*/
			//logger.fatal("FATAL log");
			
		};
	}
}
