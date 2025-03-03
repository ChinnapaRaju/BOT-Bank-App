package com.net.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
	@SequenceGenerator(name = "sg", sequenceName = "s1", initialValue = 100000001, allocationSize = 1)
	@GeneratedValue(generator = "sg", strategy = GenerationType.SEQUENCE)
	@Column(name = "customerid", length = 9)
	private Integer customerid;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit.")
	@Column(name = "customerpassword")
	private String customerpassword;

	@Email(message = "Invalid email format")
	@Column(unique = true, length = 100, name = "customermail")
	@NotNull
	private String customermail;

	@NotNull
	@Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in international format (e.g., +123456789)")
	@Column(name = "customerphonenumber")
	private String customerphonenumber;

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomerpassword() {
		return customerpassword;
	}

	public void setCustomerpassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}

	public String getCustomermail() {
		return customermail;
	}

	public void setCustomermail(String customermail) {
		this.customermail = customermail;
	}

	public String getCustomerphonenumber() {
		return customerphonenumber;
	}

	public void setCustomerphonenumber(String customerphonenumber) {
		this.customerphonenumber = customerphonenumber;
	}

	// Getters and Setters

}