package com.example.cytanbookstore.dao;

import com.example.cytanbookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book,Integer> {
    Book findByName(String name);
}
