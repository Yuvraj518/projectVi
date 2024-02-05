package com.example.ProjectVi.Controller;

import com.example.ProjectVi.Model.OTPValidationRequest;
import com.example.ProjectVi.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class OtpController {
    @Autowired
    OtpService otpService;
    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestBody String email) {
        try {
            otpService.sendOTP(email);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateOTP(@RequestBody OTPValidationRequest request) {
        try {
            boolean isValid = otpService.validateOTP(request.getEmail(), request.getOtp());
            if (isValid) {
                return ResponseEntity.ok("OTP validation successful");
            } else {
                return ResponseEntity.badRequest().body("Invalid OTP");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to validate OTP");
        }
    }
}
