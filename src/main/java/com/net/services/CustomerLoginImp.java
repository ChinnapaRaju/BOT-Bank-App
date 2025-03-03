package com.net.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.model.Customers;
import com.net.repo.CustomersRepository;

@Service
public class CustomerLoginImp implements CustomerLoginService {

    @Autowired
    private CustomersRepository custrepo;

    @Override
    public boolean CustomerLogin(Integer customerid, String customerpassword) {
        Optional<Customers> customer = custrepo.CustomerLoginVerification(customerid, customerpassword);
        return customer.isPresent();
    }
}
