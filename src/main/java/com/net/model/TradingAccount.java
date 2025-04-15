package com.net.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tradingaccounts")
@Data
@RequiredArgsConstructor
public class TradingAccount {
	@Id
	@NotNull
	@Pattern(regexp = "^[A-Z0-9]{10,20}$", message = "Account number must be alphanumeric and between 10-20 characters")
	@Column(name = "accountnumber", unique = true, length = 20)
	private String accountnumber;

	@Pattern(regexp = "^[A-Za-z ]{2,100}$", message = "Bank name must contain only letters and spaces (2-100 characters)")
	@Column(name = "bankname")
	private String bankname;

	@Pattern(regexp = "^[A-Za-z ]{2,50}$", message = "Name must contain only letters and spaces (2-50 characters)")
	@Column(name = "accountholdername")
	private String accountholdername;

	@Column(name = "ifsc")
	@Pattern(regexp = "^[A-Za-z]{4}[0-9]{7}$", message = "Invalid IFSC code format")
	private String ifsc;

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

	@CreationTimestamp
	@Column(name = "createdate", updatable = false, nullable = false)
	private LocalDateTime createdate;

	@UpdateTimestamp
	@Column(name = "lastupdate")
	private LocalDateTime lastupdate;

	@Version
	@Column(name = "updatecount")
	private Long updatecount;

	@Transient // This annotation indicates that this field should not be persisted in db
	@Pattern(regexp = "^\\d{6}$", message = "OTP must be exactly 6 digits.")
	private String OTP; // Holds only 6 digits

	// Many-to-one relationship with Customer
	@ManyToOne
	@JoinColumn(name = "customerid", nullable = false) // Foreign key column in tradingaccounts table
	private Customer customer; // Many trading accounts belong to one customer

}