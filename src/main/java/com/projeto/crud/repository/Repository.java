package com.projeto.crud.repository;

import com.projeto.crud.model.Model;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<Model, Integer> {
    Optional<User> findById(Long id);
}
