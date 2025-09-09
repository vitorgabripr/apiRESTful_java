package com.projeto.crud.controller;

import com.projeto.crud.exception.UserNotFound;
import com.projeto.crud.model.Model;
import com.projeto.crud.repository.Repository;
import com.projeto.crud.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class Controller {

    private final Repository repository;
    private Service service;

    public Controller(Service service, Repository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Model> findAll() {
        return (List<Model>) service.getAllUsers();
    }

    @GetMapping("/{id}")
    public Model getOne(@PathVariable Long id) {
        return service.getUserById(id).orElseThrow(() -> new UserNotFound("User not found"));
    }

    @PostMapping
    public Model create(@RequestBody Model user) {
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @PutMapping("/{id}/nome")
    public ResponseEntity<String> updateName(@PathVariable Long id, @RequestBody String nome) {
        Model model = repository.findById(id).orElseThrow(() -> new UserNotFound("User not found"));
//        model.setNome(nome);
//        repository.save(model);
        return ResponseEntity.ok("ID: " + id );
    }
}