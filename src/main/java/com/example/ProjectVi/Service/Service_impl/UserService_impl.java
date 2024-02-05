package com.example.ProjectVi.Service.Service_impl;

import com.example.ProjectVi.Controller.OtpController;
import com.example.ProjectVi.Model.OTPValidationRequest;
import com.example.ProjectVi.Model.User;
import com.example.ProjectVi.Repository.UserRepository;
import com.example.ProjectVi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService_impl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    OtpController otpController;
    @Override

    public void sendOTP(String email){
        otpController.sendOTP(email);
    }
    @Override
    public User createUser(User user,String otp) {
        OTPValidationRequest otpValidationRequest=new OTPValidationRequest();
        otpValidationRequest.setEmail(user.getEmail());
        otpValidationRequest.setOtp(otp);
        String otpStatus= String.valueOf(otpController.validateOTP(otpValidationRequest));
        //String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(int id,String newName) throws Exception {
        Optional<User> op1=userRepository.findById(id);
        if(op1.isEmpty()){
            throw  new Exception("User not found");
        }
        User user=op1.get();
        user.setName(newName);
        userRepository.save(user);
        return;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
