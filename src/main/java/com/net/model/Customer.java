package com.net.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "customers") // Table name is "customers"
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerid")
	private Integer customerid;

	@NotNull(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be a valid 10-digit Indian number.")
	@Column(name = "customerphonenumber", unique = true)
	private String customerphonenumber;

	@NotNull(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,30}$", message = "Password must contain 8-30 characters, including one uppercase, one lowercase, and one digit.")
	@Column(name = "customerpassword")
	private String customerpassword;

	@Email(message = "Invalid email format")
	@NotNull(message = "Email is required")
	@Column(unique = true, length = 100, name = "customermail")
	private String customermail;

	@NotBlank(message = "Full name is required")
	@Pattern(regexp = "^[a-zA-Z]+([\\s.][a-zA-Z]+)*$", message = "Full name must contain only letters, spaces, and periods.")
	@Size(max = 35, message = "Full name must be at most 35 characters long")
	@Column(name = "fullname", length = 35)
	private String fullname;

	@NotBlank(message = "Aadhaar number is required")
	@Pattern(regexp = "^\\d{12}$", message = "Aadhaar number must be exactly 12 digits")
	@Column(name = "aadhaarnumber", length = 12, unique = true)
	private String aadhaarnumber;

	@NotBlank(message = "PAN is required")
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN format. It should be in the format XXXXX1234X")
	@Column(name = "pannumber", length = 10, unique = true)
	private String pannumber;

	@NotBlank(message = "Address is required")
	@Size(max = 100, message = "Address must be up to 100 characters long")
	@Column(name = "address", length = 100)
	private String address;

	@NotNull(message = "Date of Birth is required")
	@Past(message = "Date of Birth must be in the past")
	@Column(name = "dateofbirth")
	private Date dateofbirth;

	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "^(M|F|O)$", message = "Gender must be 'M' (Male), 'F' (Female), or 'O' (Other)")
	@Column(name = "gender")
	private String gender;
	
	@NotNull
    @Pattern(regexp = "(CUSTOMER|EMPLOYEE|MANAGER)", message = "Role name must be either CUSTOMER, EMPLOYEE, or MANAGER")
    @Column(name = "rolename", length = 50, nullable = false)
    private String rolename="CUSTOMER";

	@Transient // This annotation indicates that this field should not be persisted
	@Pattern(regexp="^\\d{6}$", message="OTP must be exactly 6 digits.")
	private String OTP; // Holds only 6 digits
	

	


}