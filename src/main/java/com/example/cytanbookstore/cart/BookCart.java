package com.example.cytanbookstore.cart;

import com.example.cytanbookstore.entities.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ApplicationScope
public class BookCart {

    private List<BookDto> bookDtoList = new ArrayList<>();

    public void addBook(BookDto bookDto){
        bookDtoList.add(bookDto);
    }

    private BookDto changeNewBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getName(),
                book.getPublishDate(),
                book.getAuthor(),
                book.getImageUrl(),
                book.getPrice(),
                book.getGenreList()
        );
    }

    public int getCartSize(){
        return bookDtoList.size();
    }

    public List<BookDto> getAllBootDto(){
        return bookDtoList;
    }

    public void clearAll(){
        bookDtoList.clear();
    }

    public void removeBookDto(BookDto bookDto){
        bookDtoList.remove(bookDto);
    }

}
