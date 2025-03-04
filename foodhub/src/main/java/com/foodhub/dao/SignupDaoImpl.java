package com.foodhub.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import java.util.regex.Pattern;

@Repository("SignupDaoImpl")
public class SignupDaoImpl implements SignupDao {
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    // Phone validation pattern
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\+?[1-9][0-9]{7,14}$");
    
    @Override
    @Transactional
    public String addUser(String name, String email, String password, String phone, String address) {
        try {
            // Input validation
            if (!validateInputs(name, email, password, phone, address)) {
                return "Invalid input parameters";
            }
            
            // Check email uniqueness
            if (isEmailExists(email)) {
                return "Email already exists";
            }
            
            // Password validation
            if (!isPasswordValid(password)) {
                return "Password must be at least 8 characters long and contain at least one number, " +
                       "one uppercase letter, one lowercase letter, and one special character";
            }
           
            return "Done";
            
        } catch (Exception e) {
            throw new RuntimeException("Error registering user: " + e.getMessage());
        }
    }
    
    private boolean validateInputs(String name, String email, String password, String phone, String address) {
        return name != null && !name.trim().isEmpty() &&
               email != null && EMAIL_PATTERN.matcher(email).matches() &&
               password != null && !password.trim().isEmpty() &&
               phone != null && PHONE_PATTERN.matcher(phone).matches() &&
               address != null && !address.trim().isEmpty();
    }
    
    private boolean isPasswordValid(String password) {
        // Password must be at least 8 characters long and contain:
        // - At least one number
        // - At least one uppercase letter
        // - At least one lowercase letter
        // - At least one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
    
    private boolean isEmailExists(String email) {
        try {
            String hql = "SELECT COUNT(u) FROM User u WHERE u.email = :email and u.isVerified=true";
            Query query = entityManager.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        }
    }
}