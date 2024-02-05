package com.example.ProjectVi.Repository;

import com.example.ProjectVi.Model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OTP,Integer> {
    Optional<OTP> findByEmail(String email);
}
