package com.example.cytanbookstore.controller;

import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.entities.User;
import com.example.cytanbookstore.service.CartService;
import com.example.cytanbookstore.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "/cart/checkout")
    public ResponseEntity<?> checkout(@RequestBody String username){

        System.out.println("Username : "+username);
        //userDetailsService.checkOut((User) userDetailsService.loadUserByUsername(us),cartService.allBooksInCart());
        //cartService.clearCart();
        userDetailsService.checkOut(userDetailsService.findByUsername(username), cartService.allBooksInCart());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bookshelf")
    public ResponseEntity<List<BookDto>> myBookshelf(@RequestBody String username){
        System.out.println("Active Username : "+username);
        User activeUser = (User)userDetailsService.findByUsername(username);
        List<BookDto> myBooks = userDetailsService.getBookByUserId(activeUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(myBooks);
    }

}
