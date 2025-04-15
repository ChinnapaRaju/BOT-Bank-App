package com.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.net.model.Customer;
import com.net.repo.CustomersRepository;
import com.net.services.OTP;

@Controller
public class CustomerRegistrationController {

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private OTP otpService; // Autowire OTP service

    @GetMapping("/registercustomer")
    public String showRegisterCustomer(Model model) {
        model.addAttribute("cust", new Customer());
        return "customerregistrationfile";
    }

    @PostMapping("/registercustomer")
    public String customerRegistration(@ModelAttribute("cust") Customer cust,
                                       @RequestParam(value = "otpVerified", required = false, defaultValue = "false") boolean otpVerified,
                                       Model model) {
        try {
            if (customersRepository.existsByCustomermail(cust.getCustomermail()) ||
                    customersRepository.existsByCustomerphonenumber(cust.getCustomerphonenumber())) {
                model.addAttribute("error", "The Customer is already registered. Please log in.");
                return "customerregistrationfile";
            }

            if (!otpVerified) {
                model.addAttribute("error", "OTP verification failed. Please try again.");
                return "customerregistrationfile";
            }

           Customer customer= customersRepository.save(cust);
            otpService.sendCustomerDetails(customer.getCustomerid(), customer.getCustomerpassword());
            model.addAttribute("message", "Registration successful! Please log in.");
            return "welcomepage";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed! Please try again.");
            return "customerregistrationfile";
        }
    }
}
