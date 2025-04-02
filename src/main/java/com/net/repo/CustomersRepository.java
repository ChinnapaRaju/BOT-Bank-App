package com.net.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.net.model.Customer;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    // Query to verify customer login credentials
    @Query("SELECT c FROM Customer c WHERE c.customerid = :customerid AND c.customerpassword = :customerpassword")
    Optional<Customer> CustomerLoginVerification(Integer customerid, String customerpassword);

    // Check if a customer with the given email already exists
    boolean existsByCustomermail(String customermail);

    // Check if a customer with the given phone number already exists
    boolean existsByCustomerphonenumber(String customerphonenumber);
}