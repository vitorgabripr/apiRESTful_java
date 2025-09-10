package com.projeto.crud.controller;

import com.projeto.crud.exception.UserNotFound;
import com.projeto.crud.model.Model;
import com.projeto.crud.repository.Repository;
import com.projeto.crud.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired

    private Repository repository;

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestParam String name
            , @RequestParam String email) {

        Model n = new Model();
        n.setName(name);
        n.setEmail(email);
        repository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Model> getAll() {
        //return a json with users
        return repository.findAll();
    }

    private Service service;

    public Controller(Service service) {
        this.service = service;
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

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateName(@PathVariable Long id, @RequestBody Model model) {
        Model updatedUser = service.updateUser(id, model.getName());
        return ResponseEntity.ok(updatedUser);
    }
}