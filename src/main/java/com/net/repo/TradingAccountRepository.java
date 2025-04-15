package com.net.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.net.model.TradingAccount;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccount, String> {

	public abstract boolean existsById(String accountnumber);

}
