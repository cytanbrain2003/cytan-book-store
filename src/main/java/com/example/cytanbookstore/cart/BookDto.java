package com.example.cytanbookstore.cart;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class BookDto {

    private Integer id;
    private String name;
    private String publishDate;
    private String author;
    private String imageUrl;
    private double price;

}
