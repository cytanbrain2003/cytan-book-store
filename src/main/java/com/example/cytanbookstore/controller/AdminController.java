package com.example.cytanbookstore.controller;

import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.dao.BookDao;
import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @GetMapping("/admin/book")
    public String adminAllBooks(Model model){
        model.addAttribute("books",bookService.findAllBook());
        return "admin/book-edit-pannel";
    }

    @GetMapping("/admin/book/add")
    public String addBookPage(Model model){
        model.addAttribute("book",new Book());
        return "admin/book-add";
    }

}
