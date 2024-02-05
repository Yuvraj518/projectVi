package com.example.ProjectVi.Service;

import com.example.ProjectVi.Model.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user,String otp);

    Optional<User> getUser(int id);

    void updateUser(int id,String newName) throws Exception;

    void deleteUser(int id);

    void sendOTP(String email);
}
