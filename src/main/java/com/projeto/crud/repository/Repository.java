package com.projeto.crud.repository;

import com.projeto.crud.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<Model, Long> {
    Optional<Model> findById(Long id);
}