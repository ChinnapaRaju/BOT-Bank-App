package com.net.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tradingaccounts")
@Data
@RequiredArgsConstructor
public class TradingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountid")
	private Long accountid;

	@NotNull
	@Pattern(regexp = "^[A-Z0-9]{10,20}$", message = "Account number must be alphanumeric and between 10-20 characters")
	@Column(name = "accountnumber", unique = true, length = 20)
	private String accountnumber;

	@NotNull
	@Pattern(regexp = "(SAVINGS|CHECKING|MARGIN|INVESTMENT)", message = "Account type must be one of: SAVINGS, CHECKING, MARGIN, INVESTMENT")
	@Column(name = "accounttype", length = 20)
	private String accounttype;

	@NotNull
	@Pattern(regexp = "(ACTIVE|DELETED)", message = "Status must be either ACTIVE or DELETED")
	@Column(name = "status")
	private String status = "ACTIVE";

	@NotNull
	@DecimalMin(value = "0.00", message = "Balance cannot be negative")
	@Column(name = "balance", precision = 18, scale = 2)
	private BigDecimal balance = BigDecimal.ZERO;

	@NotNull
	@Digits(integer = 16, fraction = 2, message = "Profit/Loss must be a valid monetary value")
	@Column(name = "profitloss", precision = 18, scale = 2)
	private BigDecimal profitloss = BigDecimal.ZERO;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdate", updatable = false, nullable = false)
	private Timestamp createdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdate")
	private Timestamp lastupdate;

	// Many-to-one relationship with Customer
	@ManyToOne
	@JoinColumn(name = "customerid", nullable = false) // Foreign key column in tradingaccounts table
	private Customer customer; // Many trading accounts belong to one customer

//	// Default Constructor
//	public TradingAccount() {
//		this.createdate = new Timestamp(System.currentTimeMillis()); // Initialize creation timestamp
//	}

	
}