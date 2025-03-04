package com.foodhub.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.foodhub.entities.User;
import com.foodhub.repository.UserRepository;

import java.util.Optional;

@Service
public class EmailVerificationService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmailAndIsVerified(user.getEmail(), true);
        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        // Save user but not verified yet
        userRepository.save(user);
        
        // Send verification email
        sendVerificationEmail(user);
        return "Verification email sent!";
    }

    public void sendVerificationEmail(User user) {
        String verificationLink = "http://192.168.0.106:8081/SignupController/verify?token=" + user.getVerificationToken();
        String subject = "Verify Your Email - FoodHub";
        String content = "<p>Hello " + user.getName() + ",</p>"
                        + "<p>Click the link below to verify your email:</p>"
                        + "<p><a href=\"" + verificationLink + "\">Verify Email</a></p>";

        sendEmail(user.getEmail(), subject, content);
    }

    private void sendEmail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String verifyUser(String token) {
        Optional<User> userOptional = userRepository.findByVerificationToken(token);
        
        // if (userOptional.isEmpty()) {
        //     return "Invalid verification token!";
        // }

        User user = userOptional.get();
        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);
        
        return "Email verified successfully!";
    }
}