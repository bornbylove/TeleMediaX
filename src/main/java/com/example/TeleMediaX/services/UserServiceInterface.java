package com.example.TeleMediaX.services;

import com.example.TeleMediaX.model.AuthenticationRequest;
//import com.example.TeleMediaX.model.Role;

import java.util.List;

public interface UserServiceInterface {
    void saveUser(AuthenticationRequest user);
    //Role saveRole(Role role);
    //void addRoleToUser(String userName, String roleName);
    AuthenticationRequest getUser(String userName);
    List<AuthenticationRequest> getUser();
}