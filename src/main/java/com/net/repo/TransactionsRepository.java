package com.net.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.net.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	@Query("SELECT t FROM Transactions t WHERE t.customerid = :customerid")
	List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid);


}
