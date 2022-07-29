package com.example.cytanbookstore.service;

import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.dao.BookDao;
import com.example.cytanbookstore.dao.UserBookDao;
import com.example.cytanbookstore.dao.UserDao;
import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.entities.Role;
import com.example.cytanbookstore.entities.User;
import com.example.cytanbookstore.entities.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.cytanbookstore.entities.Role.*;

@Service
public class CustomUserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserBookDao userBookDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BookService bookService;

    public User loadUserByUsername(String username){
        return userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not found"));
    }


    @Transactional
    public User register(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Transactional
    public void checkOut(User user, List<BookDto> bookList){
        List<Book> checkOutBookList = bookService.changeDtoListToBookList(bookList);
        UserBook userBook = new UserBook();
        userBook.setUser(userDao.save(user));
        for (Book book : checkOutBookList){
            userBook.addBook(book);
        }
        userBookDao.save(userBook);
    }

    @Transactional
    public User findByUsername(String username){
        return userDao.findByUsername(username).orElse(null);
    }

    @Transactional
    public List<BookDto> getBookByUserId(int id){
        //List<List<Book>> userBookBookList = new ArrayList<>();
        /*List<Integer> mybooksId = new ArrayList<>();
        List<UserBook> userBookList = userBookDao.findUserBookByUserId(id);
        if (userBookList != null) {
            for (UserBook userbook : userBookList){
                for (Book book : userbook.getBookList()){
                    mybooksId.add(book.getId());
                }
            }
            return mybooks;

         */
        List<Book> mybooks = userBookDao.findUserBookByUserId(id);
        return bookService.changeBookListToDtoList(mybooks);
    }

}
