package com.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.net.model.Customers;
import com.net.repo.CustomersRepository;

@Controller
public class CustomerRegistrationController {

    @Autowired
    private CustomersRepository customersRepository;

    // Display registration form
    @GetMapping("/registercustomer")
    public String showRegisterCustomer(Model model) {
        model.addAttribute("cust", new Customers());
        return "customerregistrationfile"; // JSP file name
    }

    // Handle customer registration
    @PostMapping("/registercustomer")
    public String customerRegistration(@ModelAttribute("cust") Customers cust, Model model) {
        try {
            // Check if the email or phone number already exists
            if (customersRepository.existsByCustomermail(cust.getAadhaarnumber()) || 
                customersRepository.existsByCustomerphonenumber(cust.getCustomerphonenumber())) {
                model.addAttribute("error", "The Customer is already registered plz Login.");
                return "customerregistrationfile"; // Reload form with error
            }
            
            // Save customer
            Integer idd= customersRepository.save(cust).getCustomerid();
            String pass=cust.getCustomerpassword();
            model.addAttribute("message", "Registration successful! Please login.");
            return "welcomepage"; // Redirect to welcome page
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed! Please try again.");
            return "customerregistrationfile"; // Reload form with error
        }
    }
}
