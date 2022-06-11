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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserBookDao userBookDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userDao.findByUsername(username);
        if (!users.isPresent()){
            throw new UsernameNotFoundException("UserName is not exist");
        }
        return users.get();
    }

    @Transactional
    public User register(User user){
        user.setRole(ROLE_PREFIX+USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Transactional
    public void checkOut(User user, List<BookDto> bookList){
        List<Book> checkOutBookList = changeDtoToBook(bookList);
        UserBook userBook = new UserBook();
        userBook.setUser(userDao.save(user));
        for (Book book : checkOutBookList){
            userBook.addBook(book);
        }
        userBookDao.save(userBook);
    }

    private List<Book> changeDtoToBook(List<BookDto> bookDtoList){
        List<Book> checkOutBookList = new ArrayList<>();
        for (BookDto bookDto : bookDtoList){
            checkOutBookList.add(bookDao.getById(bookDto.getId()));
        }
        return checkOutBookList;
    }

    @Transactional
    public List<Book> getBookByUserId(int id){
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
        return mybooks;
    }
}
