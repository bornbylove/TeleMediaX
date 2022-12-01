package com.example.TeleMediaX.services;

import com.example.TeleMediaX.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    @Lazy
    private SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user from database
        return new User("John","$2a$12$wYxu/O5W6C9WWqzrkmaYtelfWpj8rri6JU8i9vCWzhvkZMPUfSkBm", new ArrayList<>());

    }
}
