package com.projeto.crud.repository;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<User, Long> {

}
