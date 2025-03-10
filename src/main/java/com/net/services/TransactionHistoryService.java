package com.net.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.net.model.Transactions;

@Service
public interface TransactionHistoryService {

	public List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid, Date start, Date end);

}
