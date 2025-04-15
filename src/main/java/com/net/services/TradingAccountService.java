package com.net.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.model.TradingAccount;
import com.net.repo.TradingAccountRepository;

@Service
public class TradingAccountService {

	@Autowired
	private TradingAccountRepository tradingAccountRepository;
	
	public boolean checkVeryAccount(String accountnumber) throws Exception{
        return tradingAccountRepository.existsById(accountnumber);
    }
	
	public boolean addAccount(TradingAccount accountdetails) {
		try {
		tradingAccountRepository.save(accountdetails);
		return true;
		}catch (Exception e) {
			return false;
		}
	}

}
