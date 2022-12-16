package com.example.TeleMediaX.services;

import com.example.TeleMediaX.model.AuthenticationRequest;
//import com.example.TeleMediaX.model.Role;
//import com.example.TeleMediaX.repo.RoleRepo;
import com.example.TeleMediaX.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserServiceInterface{

    private UserRepo userRepo;
   // private RoleRepo roleRepo;
    //private  Role role;
    @Override
    public AuthenticationRequest saveUser(AuthenticationRequest user) {
        log.info("Saving new role to Database");
        return userRepo.save(user);
    }

   /* @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to the database",role.getName());
        return roleRepo.save(role);
    }*/

   /* @Override
    public void addRoleToUser(String userName, String roleName) {
        AuthenticationRequest user = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }*/

    @Override
    public AuthenticationRequest getUser(String userName) {
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<AuthenticationRequest> getUser() {
        return userRepo.findAll();
    }
}
