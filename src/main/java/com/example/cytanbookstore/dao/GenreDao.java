package com.example.cytanbookstore.dao;

import com.example.cytanbookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre,Integer> {
}
