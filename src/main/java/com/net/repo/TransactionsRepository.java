package com.net.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.net.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

    // Query to fetch transaction history for a customer within a date range
    @Query("SELECT t FROM Transactions t WHERE t.customer.customerid = :customerid AND t.transactiondate BETWEEN :start AND :end")
    List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid, Date start, Date end);
}