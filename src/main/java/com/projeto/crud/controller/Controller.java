package com.projeto.crud.controller;

import com.projeto.crud.service.Service;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        return service.getUserById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}