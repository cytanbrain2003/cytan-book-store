package com.example.cytanbookstore.controller;

import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.dao.BookDao;
import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.entities.User;
import com.example.cytanbookstore.service.BookService;
import com.example.cytanbookstore.service.CartService;
import com.example.cytanbookstore.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBook());
    }

    @DeleteMapping("/book/remove/{id}")
    public ResponseEntity removeBook(@PathVariable("id")int id){
        System.out.println("Delete Id : "+id);
        if(bookService.removeBook(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(value = "/book/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable("id")int id, @RequestBody BookDto bookDto){
        Book book = bookService.updateBook(id,bookDto);
        if (book != null){
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/book/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        Book book = bookService.addBook(bookDto);
        if(book.getId() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cart/add")
    public ResponseEntity<List<BookDto>> addToCart(@RequestBody BookDto bookDto){
        System.out.println("Cart Add");
        cartService.addBookToCart(bookDto);
        return ResponseEntity.status(HttpStatus.OK).body(cartService.allBooksInCart());
    }

    @GetMapping("/cart")
    public ResponseEntity<List<BookDto>> cartView(Model model){
        List<BookDto> cartedBooks = cartService.allBooksInCart();
        return ResponseEntity.status(HttpStatus.OK).body(cartedBooks);
    }

    @GetMapping("/cart/clear-all")
    public ResponseEntity<Integer> clearCart(){
        cartService.clearCart();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cartService.cartSize());
    }

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestBody BookDto bookDto){
        cartService.removeFromCart(bookDto);
        return "redirect:/book/cart";
    }
    @GetMapping("/book/cart/info")
    public String cartItemInfo(@RequestParam("bookId")int id,Model model){
        model.addAttribute("bookInfo",bookService.findBook(id));
        model.addAttribute("cartedBooks",cartService.allBooksInCart());
        return "user/cart-view";
    }

}
