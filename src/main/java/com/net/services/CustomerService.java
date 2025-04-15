package com.net.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.model.Customer;
import com.net.repo.CustomersRepository;

@Service
public class CustomerService {

	    @Autowired
	    private CustomersRepository customerRepository;
	    
	    public Optional<Customer> getCustomerById(Integer customerId) {
	        return customerRepository.findByCustomerId(customerId);
	      }
	
}
