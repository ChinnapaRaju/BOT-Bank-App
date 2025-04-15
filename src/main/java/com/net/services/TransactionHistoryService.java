package com.net.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.net.model.Transactions;

@Service
public interface TransactionHistoryService {
    // Fetch transaction history for a customer within a date range
    List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid, Date start, Date end);
}