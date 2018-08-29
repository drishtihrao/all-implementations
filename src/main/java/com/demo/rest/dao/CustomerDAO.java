package com.demo.rest.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.rest.pojo.Customer;

@Repository
public interface CustomerDAO extends MongoRepository<Customer, ObjectId> {
	Customer findByCustomerId(int customerId);

}
