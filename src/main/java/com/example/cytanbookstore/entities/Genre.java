package com.example.cytanbookstore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "genreList")
    private List<Book> bookList;
}
