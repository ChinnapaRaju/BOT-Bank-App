package com.net.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.model.Transactions;
import com.net.repo.TransactionsRepository;

@Service
public class TransactionHistoryserviceImpl implements TransactionHistoryService{

	@Autowired
	private TransactionsRepository historyService;
	
	@Override
	public List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid,Date start,Date end) {
		return historyService.getTransactionHistoryBasedOnCustId(customerid,start,end);
	}

}
 