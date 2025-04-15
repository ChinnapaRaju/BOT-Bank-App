package com.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.net.services.OTP;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestControllerClass {

    @Autowired
    private OTP otpService;

    @GetMapping("/customerotp/send")
    public Map<String, Object> sendOTP(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            otpService.sendOTP(email);
            response.put("success", true);
            response.put("message", "OTP sent successfully.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to send OTP.");
        }
        return response;
    }

    @GetMapping("/customerotp/verify")
    public Map<String, Object> verifyOTP(@RequestParam String email, @RequestParam String otp) {
        Map<String, Object> response = new HashMap<>();
        if (otpService.verifyOTP(email, otp)) {
            response.put("success", true);
            response.put("message", "OTP Verified Successfully.");
        } else {
            response.put("success", false);
            response.put("message", "Invalid OTP.");
        }
        return response;
    }
}
