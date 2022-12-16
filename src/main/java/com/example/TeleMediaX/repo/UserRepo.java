package com.example.TeleMediaX.repo;

import com.example.TeleMediaX.model.AuthenticationRequest;
import com.example.TeleMediaX.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AuthenticationRequest, Long> {
    AuthenticationRequest findByUserName(String userName);
}
