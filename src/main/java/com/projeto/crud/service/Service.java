package com.projeto.crud.service;

import com.projeto.crud.model.Model;
import com.projeto.crud.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final Repository Repository;

    public Service(Repository Repository) {
        this.Repository = Repository;
    }

    public Iterable<Model> getAllUsers() {
        return Repository.findAll();
    }

    public Optional<Model> getUserById(Long id) {
        return Repository.findById(id);
    }

    public Model saveUser(Model user) {
        return (Model) Repository.save(user);
    }

    public void deleteUser(Long id) {
        Repository.deleteById(id);
    }
}
