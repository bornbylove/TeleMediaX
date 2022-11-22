package com.example.TeleMediaX.controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class UserControllerService {
    private Integer id;
    private String name;
    private LocalDate birthDate;

    public UserControllerService() {
    }

    public UserControllerService(Integer id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserControllerService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
