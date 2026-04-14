package com.AI.ML.AIandML.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AI.ML.AIandML.dto.LoginRequest;
import com.AI.ML.AIandML.dto.RegisterRequest;
import com.AI.ML.AIandML.entity.User;
import com.AI.ML.AIandML.repository.UserRepository;
import com.AI.ML.AIandML.util.OtpUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    // REGISTER NEW USER
    public String register(RegisterRequest request) {
        // Check if user already exists
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists with this email";
        }

        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getName());

        repo.save(user);
        return "User registered successfully";
    }

    // NORMAL LOGIN
    public String login(LoginRequest request) {

        User user =
                repo.findByEmail(request.getEmail())
                        .orElse(null);

        if (user == null)
            return "User not found";

        if (encoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return "Login Success";
        }

        return "Invalid Password";
    }

    // SEND OTP
    public String sendOtp(String email) {

        User user =
                repo.findByEmail(email)
                        .orElse(null);

        if (user == null)
            return "User not found";

        String otp =
                OtpUtil.generateOtp();

        user.setOtp(otp);

        user.setOtpExpiry(
                System.currentTimeMillis()
                        + 5 * 60 * 1000 // 5 min
        );

        repo.save(user);

        try {
            emailService.sendOtp(email, otp);
        } catch (Exception e) {
            // Email failed, but OTP is saved - still return success for testing
            System.out.println("OTP for " + email + ": " + otp);
        }

        return "OTP: " + otp + " (Check email or console)";
    }

    // VERIFY OTP
    public String verifyOtp(
            String email,
            String otp) {

        User user =
                repo.findByEmail(email)
                        .orElse(null);

        if (user == null)
            return "User not found";

        if (!otp.equals(user.getOtp()))
            return "Invalid OTP";

        if (user.getOtpExpiry()
                < System.currentTimeMillis())
            return "OTP Expired";

        user.setOtp(null);

        repo.save(user);

        return "OTP Login Success";
    }
}