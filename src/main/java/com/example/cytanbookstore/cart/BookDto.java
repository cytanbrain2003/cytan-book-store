package com.example.cytanbookstore.cart;

import com.example.cytanbookstore.entities.Genre;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class BookDto {

    private Integer id;
    private String name;
    private String publishDate;
    private String author;
    private String imageUrl;
    private double price;
    private List<Genre> genreList;

}
