package com.net.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for MySQL AUTO_INCREMENT
    @Column(name = "customerid")
    private Integer customerid;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be a valid 10-digit Indian number.")
    @Column(name = "customerphonenumber", unique = true)
    private String customerphonenumber;

    @NotNull(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,30}$",
            message = "Password must contain 8-30 characters, including one uppercase, one lowercase, and one digit.")
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

    // Getters and Setters
    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomerphonenumber() {
        return customerphonenumber;
    }

    public void setCustomerphonenumber(String customerphonenumber) {
        this.customerphonenumber = customerphonenumber;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAadhaarnumber() {
        return aadhaarnumber;
    }

    public void setAadhaarnumber(String aadhaarnumber) {
        this.aadhaarnumber = aadhaarnumber;
    }

    public String getPannumber() {
        return pannumber;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
