package com.foodhub.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("LoginDaoImpl")
public class LoginDaoImpl implements LoginDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean validateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND BINARY password = ? AND is_verified=true";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        return count > 0;
    }
    @Override
    public boolean checkMail(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND is_verified=true";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }

}
