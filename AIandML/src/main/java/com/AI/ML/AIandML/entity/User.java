package com.AI.ML.AIandML.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    private String otp;

    private Long otpExpiry;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Long getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(Long otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public User(Long id, String username, String email, String password, String role, String otp, Long otpExpiry) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.otp = otp;
		this.otpExpiry = otpExpiry;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    // Getters and Setters
}