package com.net.services;

import org.springframework.stereotype.Service;

@Service
public interface CustomerLoginService {
    boolean CustomerLogin(Integer customerid, String customerpassword);
}
