package com.projeto.crud.controller;

import com.projeto.crud.exception.UserNotFound;
import com.projeto.crud.model.Model;
import com.projeto.crud.repository.Repository;
import com.projeto.crud.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired

    private Repository repository;

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestParam String name
            , @RequestParam String email) {

        logger.debug("addUser called name={} email={}", name, email);
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

    private final Service service;

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
    public ResponseEntity<?> create(@RequestBody Model user) {
        logger.debug("create called payload={}", user);
        // Validar campos vazios ou nulos
        String name = user.getName();
        String email = user.getEmail();
        
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return ResponseEntity
                .badRequest()
                .body("Nome e email são obrigatórios e não podem estar vazios");
        }
        
        // Limpar espaços em branco e salvar
        //ta limpando as variaveis antes de salvar / arrumar 
        // user.setName(name.trim());
        // user.setEmail(email.trim());
        
        Model savedUser = service.saveUser(user);
        logger.debug("create saved id={}", savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Model model) {
        logger.debug("updateUser called id={} payload={}", id, model);
        Model existingUser = service.getUserById(id)
            .orElseThrow(() -> new UserNotFound("User not found"));
        
            // Validar campos vazios ou nulos
            String name = model.getName();
            String email = model.getEmail();
        
            if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return ResponseEntity
                .badRequest()
                    .body("Nome e email são obrigatórios e não podem estar vazios");
        }
        
            // Atualizar com os valores validados
            existingUser.setName(name.trim());
            existingUser.setEmail(email.trim());
        
        Model updatedUser = service.saveUser(existingUser);
        logger.debug("updateUser saved id={}", updatedUser.getId());
        return ResponseEntity.ok(updatedUser);
    }
}