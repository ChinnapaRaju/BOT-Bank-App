package com.net.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTP {

    @Autowired
    private EmailService emailService;

    private final ConcurrentHashMap<String, OTPDetails> otpStorage = new ConcurrentHashMap<>();

    public void sendOTP(String email) {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // Ensures a 6-digit OTP

        OTPDetails otpDetails = new OTPDetails(String.valueOf(otp), LocalDateTime.now().plusMinutes(5));
        otpStorage.put(email, otpDetails);

        String subject = "OTP FOR REGISTRATION";
        String body = otp + " is your OTP. It is valid for **5 minutes**.";
        this.mail=email;
        emailService.sendEmail(email, subject, body);
    }

    public boolean verifyOTP(String email, String otp) {
        if (!otpStorage.containsKey(email)) {
            return false; // No OTP sent for this email
        }

        OTPDetails otpDetails = otpStorage.get(email);
        if (otpDetails.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpStorage.remove(email); // Remove expired OTP
            return false; // OTP expired
        }

        if (otpDetails.getOtp().equals(otp)) {
            otpStorage.remove(email); // Remove OTP after successful verification
            return true;
        }

        return false; // Invalid OTP
    }

    private static class OTPDetails {
        private final String otp;
        private final LocalDateTime expiryTime;

        public OTPDetails(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiryTime() {
            return expiryTime;
        }
    }
    
    private String mail;
    public void sendCustomerDetails(Integer integer, String password) {
        String subject = "BOT Trade Bank Account Details";
        String body = "Dear Customer,\n\n" +
                      "Thank you for registering with us!\n\n" +
                      "Here are your account details:\n\n" +
                      "Customer ID: " + integer + "\n" +
                      "Password: " + password + "\n\n" +
                      "Please keep this information secure.\n\n" +
                      "Sincerely,\n" +
                      "The Registration Team";

        emailService.sendEmail(this.mail, subject, body);
    }

}
