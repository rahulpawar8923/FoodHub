package com.foodhub.dao;

public interface LoginDao {

    public boolean validateUser(String email, String password);

    public boolean checkMail(String email);
}
