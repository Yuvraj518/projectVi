package com.example.ProjectVi.Service.Service_impl;

import com.example.ProjectVi.Model.OTP;
import com.example.ProjectVi.Repository.OtpRepository;
import com.example.ProjectVi.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
public class OtpService_impl implements OtpService {
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    JavaMailSender javaMailSender;
    public void sendOTP(String email) {
        String otp = generateOTP();
        // Send OTP to the provided email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP: " + otp);
        javaMailSender.send(message);

        // Save the OTP to the database
        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);
    }

    public boolean validateOTP(String email, String otp) {
        Optional<OTP> otpOptional = otpRepository.findByEmail(email);
        if (otpOptional.isPresent()) {
            OTP storedOTP = otpOptional.get();
            return storedOTP.getOtp().equals(otp);
        }
        return false;
    }

    private String generateOTP() {
        Random random=new Random();
        int x=random.nextInt(99999);
        return Integer.toString(x);
    }
}
