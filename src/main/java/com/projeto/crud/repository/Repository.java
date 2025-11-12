package com.projeto.crud.repository;
import com.projeto.crud.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.lang.NonNull;

public interface Repository extends JpaRepository<Model, Long> {
    @Override
    @NonNull Optional<Model> findById(@NonNull Long id);
};