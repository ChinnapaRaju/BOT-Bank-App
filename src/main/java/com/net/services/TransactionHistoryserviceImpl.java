package com.net.services;

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
	public List<Transactions> getTransactionHistoryBasedOnCustId(Integer customerid) {
		return historyService.getTransactionHistoryBasedOnCustId(customerid);
	}

}
 