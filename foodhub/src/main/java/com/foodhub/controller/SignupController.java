package com.foodhub.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodhub.entities.User;
import com.foodhub.services.EmailVerificationService;
import com.foodhub.services.SignupService;

@RestController
@RequestMapping(value = "/SignupController")
public class SignupController {

    @Autowired
    @Qualifier("SignupServiceImpl")
    private SignupService signupService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Map<String, String> signUp(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "phone") String phone,
        @RequestParam(value = "address") String address
    ){
        String isRight = signupService.addUser(name, email, password, phone, address);
        if (isRight=="Done") {
            User newUser = new User(name, email, password, phone, address);
            String result = emailVerificationService.registerUser(newUser);
            return Map.of("message", result);
        }else{
            return Map.of("message", isRight);
        }
        
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        String result = emailVerificationService.verifyUser(token);
        String htmlResponse;
        if ("Email verified successfully!".equals(result)) {
            htmlResponse = "<html><head><title>Verification</title>"
                         + "<script>setTimeout(function(){ window.location.href='/login.html'; }, 3000);</script>"
                         + "</head><body>"
                         + "<h1 style='text-align: center; color: green;'>" + result + "</h1>"
                         + "<p style='text-align: center;'>Redirecting to login page...</p>"
                         + "</body></html>";
        } else {
            htmlResponse = "<html><head><title>Verification</title></head><body>"
                         + "<h1 style='text-align: center; color: red;'>" + result + "</h1>"
                         + "</body></html>";
        }
    
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlResponse);
    }
}
