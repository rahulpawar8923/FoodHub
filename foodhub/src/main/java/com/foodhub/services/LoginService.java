package com.foodhub.services;

public interface LoginService {
    
    public boolean validateUser(String email, String password);

    public boolean checkMail(String email);
}
