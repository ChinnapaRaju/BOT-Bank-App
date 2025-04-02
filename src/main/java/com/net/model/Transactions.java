package com.net.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid")
    private Integer transactionid;

    @NotNull
    @Column(name = "transactiondate", nullable = false)
    private Date transactiondate; // Stores only date

    @NotNull
    @Column(name = "transactiontime", nullable = false)
    private Time transactiontime; // Stores only time

    @NotNull
    @Pattern(regexp = "^(credit|debit|failed)$", message = "Transaction type must be 'credit', 'debit', or 'failed'")
    @Column(name = "transactiontype", nullable = false)
    private String transactiontype;

    @NotNull
    @Pattern(regexp = "^\\d{4,20}$", message = "Account number must be numeric and between 4-20 digits")
    @Column(name = "accountnumber", nullable = false)
    private String accountnumber;

    @NotNull
    @Min(value = 1, message = "Processing amount must be at least 1")
    @Column(name = "processingamount", nullable = false)
    private Integer processingamount;

    @Min(value = 500, message = "Balance must be at least 500 if provided")
    @Column(name = "balance")
    private Integer balance;

    @NotNull
    @Pattern(regexp = "^(completed|pending|failed)$", message = "Status must be 'completed', 'pending', or 'failed'")
    @Column(name = "status", nullable = false)
    private String status;

    // Many-to-one relationship with Customer
    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false) // Foreign key column in transactions table
    private Customer customer; // Many transactions belong to one customer

    // Default Constructor
    public Transactions() {
    }

    // Parameterized Constructor
    public Transactions(Date transactiondate, Time transactiontime, String transactiontype,
                        String accountnumber, Integer processingamount, Integer balance, String status, Customer customer) {
        this.transactiondate = transactiondate;
        this.transactiontime = transactiontime;
        this.transactiontype = transactiontype;
        this.accountnumber = accountnumber;
        this.processingamount = processingamount;
        this.balance = balance;
        this.status = status;
        this.customer = customer;
    }

    
}