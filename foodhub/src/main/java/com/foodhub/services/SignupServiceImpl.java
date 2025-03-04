package com.foodhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodhub.dao.SignupDao;

@Service("SignupServiceImpl")
public class SignupServiceImpl implements SignupService{

    @Autowired
    @Qualifier("SignupDaoImpl")
    private SignupDao signupDao;
    @Override
    public String addUser(String name, String email, String password, String phone, String address){
        return signupDao.addUser(name,email,password,phone,address);
    }
}
