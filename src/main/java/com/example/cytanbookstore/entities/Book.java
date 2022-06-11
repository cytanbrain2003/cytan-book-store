package com.example.cytanbookstore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Book extends IdClass{

    private String name;
    private String publishDate;
    private String author;
    private String imageUrl;
    private double price;

    public Book() {
    }

    public Book(String name, String publishDate, String author, String imageUrl, double price) {
        this.name = name;
        this.publishDate = publishDate;
        this.author = author;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
