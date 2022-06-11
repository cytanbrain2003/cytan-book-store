package com.example.cytanbookstore.service;

import com.example.cytanbookstore.cart.BookCart;
import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.dao.BookDao;
import com.example.cytanbookstore.dao.UserDao;
import com.example.cytanbookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartService {

    @Autowired
    private BookCart bookCart;

    @Autowired
    private UserDao userDao;

    public void addBookToCart(Book book){

        bookCart.addBook(book);
    }

    public int cartSize(){
        return bookCart.getCartSize();
    }

    public List<BookDto> allBooksInCart(){
        return bookCart.getAllBootDto();
    }

    public void clearCart(){
        bookCart.clearAll();
    }

    public void removeFromCart(Book book){
        bookCart.removeBookDto(book);
    }

}
