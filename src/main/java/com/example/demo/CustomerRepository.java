package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstname(String firstname);
    public Customer findById(String id);
    public List<Customer> findByLastname(String lastname);
	public void deleteById(String id);

}