package com.projeto.crud.service;

import com.projeto.crud.model.Model;
import com.projeto.crud.repository.Repository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Iterable<Model> getAllUsers() {
        logger.debug("getAllUsers called");
        Iterable<Model> all = repository.findAll();
        logger.debug("getAllUsers result size unknown (iterable)");
        return all;
    }

    public Optional<Model> getUserById(Long id) {
        logger.debug("getUserById called with id={}", id);
        Optional<Model> found = repository.findById(id);
        logger.debug("getUserById found present={}", found.isPresent());
        return found;
    }

    public Model saveUser(Model user) {
        logger.debug("saveUser called with user={}", user);
        Model saved = repository.save(user);
        logger.debug("saveUser returned user id={}", saved != null ? saved.getId() : null);
        return saved;
    }

    public void deleteUser(Long id) {
        logger.debug("deleteUser called id={}", id);
        repository.deleteById(id);
    }

    public Model updateUser(Long id, String nome) {
        logger.debug("updateUser called id={} nome={}", id, nome);
        Model user = repository.findById(id)
                .orElseThrow(() -> new com.projeto.crud.exception.UserNotFound("User not found"));
        user.setName(nome);
        Model saved = repository.save(user);
        logger.debug("updateUser saved id={}", saved.getId());
        return saved;
    }

    public Model updateUserEmail(Long id, String email) {
        logger.debug("updateUserEmail called id={} email={}", id, email);
        Model user = repository.findById(id)
                .orElseThrow(() -> new com.projeto.crud.exception.UserNotFound("User not found"));
        user.setEmail(email);
        Model saved = repository.save(user);
        logger.debug("updateUserEmail saved id={}", saved.getId());
        return saved;
    }
}
