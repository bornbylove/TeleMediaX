package com.example.TeleMediaX.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ManyToAny;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    //private Long id;
    //private String name;
    private String userName;
    private String password;
    //@ManyToAny(fetch = FetchType.EAGER)
    public final Collection<Role> roles = new ArrayList<>();

    //public AuthenticationRequest() {
    //}

    /*public AuthenticationRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }*/

    /*public AuthenticationRequest(Long id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }*/

   /* public AuthenticationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
