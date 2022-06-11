package com.example.cytanbookstore.controller;

import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.entities.User;
import com.example.cytanbookstore.service.CartService;
import com.example.cytanbookstore.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CartService cartService;

    @GetMapping("/user/cart/checkout")
    public String checkout(Authentication authentication,Model model){
        userDetailsService.checkOut((User) userDetailsService.loadUserByUsername(authentication.getName()),cartService.allBooksInCart());
        cartService.clearCart();
        return "redirect:/book";
    }

    @GetMapping("/bookshelf")
    public String myBookshelf(Authentication authentication, Model model){
        User activeUser = (User)userDetailsService.loadUserByUsername(authentication.getName());
        List<Book> myBooks = userDetailsService.getBookByUserId(activeUser.getId());
        if (myBooks.isEmpty()){
            model.addAttribute("reply","-You Haven't Buy A Book Yet!");
        }
        model.addAttribute("books", myBooks);
        model.addAttribute("user",activeUser.getName());
        return "user/bookshelf";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "user/user-form";
    }

    @PostMapping("/register")
    public String doRegister(User user,BindingResult result){
        if(result.hasErrors()){
            return "user/user-form";
        }
        userDetailsService.register(user);
        return "redirect:/login";
    }

}
