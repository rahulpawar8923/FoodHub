package com.foodhub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodhub.dao.LoginDao;

@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService{

    @Autowired
    @Qualifier("LoginDaoImpl")
    private LoginDao loginDao;
    @Override
    public boolean validateUser(String email, String password) {
        return loginDao.validateUser(email, password);
    }
    @Override
    public boolean checkMail(String email) {
        return loginDao.checkMail(email);
    }

}
