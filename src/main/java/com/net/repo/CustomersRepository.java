package com.net.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.net.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

	@Query("SELECT c FROM Customers c WHERE c.customerid = :customerid AND c.customerpassword = :customerpassword")
	Optional<Customers> CustomerLoginVerification(Integer customerid, String customerpassword);

	 // Check if a customer with the given email already exists
    boolean existsByCustomermail(String customermail);

    // Check if a customer with the given phone number already exists
    boolean existsByCustomerphonenumber(String customerphonenumber);
	
}
