package com.projeto.crud.service;

import com.projeto.crud.repository.Repository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final Repository Repository;

    public Service(Repository Repository) {
        this.Repository = Repository;
    }

    public Iterable<User> getAllUsers() {
        return Repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Repository.findById(id);
    }

    public User saveUser(User user) {
        return (User) Repository.save(user);
    }

    public void deleteUser(Long id) {
        Repository.deleteById(Math.toIntExact(id));
    }
}
