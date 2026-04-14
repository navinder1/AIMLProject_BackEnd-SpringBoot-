package com.AI.ML.AIandML.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AI.ML.AIandML.dto.LoginRequest;
import com.AI.ML.AIandML.dto.OtpRequest;
import com.AI.ML.AIandML.dto.RegisterRequest;
import com.AI.ML.AIandML.dto.VerifyOtpRequest;
import com.AI.ML.AIandML.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthController {

    @Autowired
    private AuthService service;

    // REGISTER NEW USER
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {
        return service.register(request);
    }

    // LOGIN WITH PASSWORD

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return service.login(request);
    }

    // SEND OTP

    @PostMapping("/send-otp")
    public String sendOtp(
            @RequestBody OtpRequest request) {

        return service.sendOtp(
                request.getEmail());
    }

    // VERIFY OTP

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        return service.verifyOtp(
                request.getEmail(),
                request.getOtp());
    }
}