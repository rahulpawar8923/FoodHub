package com.foodhub.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodhub.services.LoginService;

@RestController
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    @Qualifier("LoginServiceImpl")
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password
    ) {
        Map<String, Object> response = new HashMap<>();
        
        boolean isValidUser = loginService.validateUser(email, password);
        if (isValidUser) {
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("redirectUrl", "homePage.html"); // Redirect to home page
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping("/checkMail")
    public ResponseEntity<Map<String, Object>> checkMail(
        @RequestParam(value = "email") String email
    ) {
        Map<String, Object> response = new HashMap<>();
        
        boolean isValidUser = loginService.checkMail(email);
        if (isValidUser) {
            response.put("status", "success");
            response.put("message", "Email Registered");
            response.put("redirectUrl", "UpdatePassword.html?email=" + email); // Redirect to home page
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid email");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
