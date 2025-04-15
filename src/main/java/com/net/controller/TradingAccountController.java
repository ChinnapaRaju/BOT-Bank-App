package com.net.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.net.model.Customer;
import com.net.model.TradingAccount;
import com.net.services.CustomerService;
import com.net.services.TradingAccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TradingAccountController {

    @Autowired
    private TradingAccountService tradingAccountService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/accounts")
    public String bankDetailsFrom(HttpSession session, Model model) {
      Integer customerId = (Integer) session.getAttribute("custID");
      if (customerId != null) {
        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        if (customerOptional.isPresent()) {
          Customer customer = customerOptional.get();
        //  model.addAttribute("banks", customer.getTradingAccounts());
          System.out.println(customer.toString());
          return "showaccounts";
        } else {
          model.addAttribute("errorMessage", "Customer not found");
          return "error"; // or "showaccounts" with a message
        }
      }
      return "redirect:/homepage"; // Or handle the case where the customer ID is not in the session
    }

    @GetMapping("/addbank")
    public String showAddBankForm(Model model) {
        model.addAttribute("tradingAccount", new TradingAccount());
        return "addbankform";
    }

    @PostMapping("/addbank")
    public String addCustomerBankAccount(@ModelAttribute TradingAccount tradingAccount, HttpSession session, Model model) {
        try {
            Integer customerId = (Integer) session.getAttribute("custID");

            if (customerId != null) {
                Optional<Customer> customer = customerService.getCustomerById(customerId);
                if (customer != null) {
                	Customer customer1=customer.get();
                    tradingAccount.setCustomer(customer1);

                    if (tradingAccountService.checkVeryAccount(tradingAccount.getAccountnumber())) {
                        tradingAccountService.addAccount(tradingAccount);
                        return "redirect:/accounts"; // Redirect to accounts page after adding bank
                    } else {
                        model.addAttribute("errorMessage", "Account already exists");
                        return "addbankform"; // Return to the form with an error message
                    }
                } else {
                    return "error"; // Handle case where customer is not found
                }
            } else {
                return "error"; // Handle case where customer ID is not found in session
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred");
            return "addbankform"; // Return to the form with an error message
        }
    }
}
