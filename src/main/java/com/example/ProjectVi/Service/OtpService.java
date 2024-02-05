package com.example.ProjectVi.Service;

public interface OtpService {
    void sendOTP(String email);

    boolean validateOTP(String email, String otp);
}
