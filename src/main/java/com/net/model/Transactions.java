package com.net.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "transactions")
public class Transactions {

	@Id
	@SequenceGenerator(name = "sgg", sequenceName = "s2", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "sgg", strategy = GenerationType.SEQUENCE)
	@Column(name = "transactionid")
	private Integer transactionid;

	@NotNull
	@Column(name = "customerid", nullable = false)
	private Integer customerid;

	@NotNull
	@Column(name = "transactiondatetime", nullable = false)
	private LocalDateTime transactiondatetime;

	@NotNull
	@Pattern(regexp = "^(credit|debit|failed)$")
	@Column(name = "transactiontype", nullable = false)
	private String transactiontype;

	@NotNull
	@Pattern(regexp = "^\\d{4,20}$")
	@Column(name = "accountnumber", nullable = false)
	private String accountnumber;

	@NotNull
	@Min(value = 1)
	@Column(name = "processingamount", nullable = false)
	private Integer processingamount;

	@Min(value = 500)
	@Column(name = "balance")
	private Integer balance;

	@NotNull
	@Pattern(regexp = "^(completed|pending|failed)$")
	@Column(name = "status", nullable = false)
	private String status;

	public Integer getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public LocalDateTime getTransactiondatetime() {
		return transactiondatetime;
	}

	public void setTransactiondatetime(LocalDateTime transactiondatetime) {
		this.transactiondatetime = transactiondatetime;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Integer getProcessingamount() {
		return processingamount;
	}

	public void setProcessingamount(Integer processingamount) {
		this.processingamount = processingamount;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", customerid=" + customerid + ", transactiondatetime="
				+ transactiondatetime + ", transactiontype=" + transactiontype + ", accountnumber=" + accountnumber
				+ ", processingamount=" + processingamount + ", balance=" + balance + ", status=" + status + "]";
	}
	
	
}
