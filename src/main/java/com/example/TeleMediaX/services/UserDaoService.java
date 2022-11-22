package com.example.TeleMediaX.services;

import com.example.TeleMediaX.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(3, "John", LocalDate.now().minusYears(46)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User deleteById(int id) {
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user){
        users.add(user);
        return user;
    }
}
