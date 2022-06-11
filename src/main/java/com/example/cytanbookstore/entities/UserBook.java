package com.example.cytanbookstore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserBook extends IdClass{

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book){
        bookList.add(book);
    }
}
