package com.example.cytanbookstore.dao;

import com.example.cytanbookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
