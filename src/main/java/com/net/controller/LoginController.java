package com.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.net.services.CustomerLoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@RequestMapping("/welcomepage")
	public String welcomePage(HttpSession session, Model model) {
		Boolean loginFailed = (Boolean) session.getAttribute("loginFailed");
		if (loginFailed != null && loginFailed) {
			model.addAttribute("errorMessage", "Your Customer ID does not match the Password. Please try again");
		}
		return "welcomepage";
	}

	@Autowired
	private CustomerLoginService custservice;

	@GetMapping("/homepagecontroller")
	public String homePage(@RequestParam Integer customerid, @RequestParam String customerpassword, Model model,
			HttpSession session) {

		session.setAttribute("custID", customerid);
		boolean isAuthenticated = custservice.CustomerLogin(customerid, customerpassword);

		if (isAuthenticated) {
			session.removeAttribute("loginFailed");
			model.addAttribute("customerid", customerid);
			return "homepage";
		} else {
			session.setAttribute("loginFailed", true);
			return "redirect:/welcomepage";
		}
	}
}
