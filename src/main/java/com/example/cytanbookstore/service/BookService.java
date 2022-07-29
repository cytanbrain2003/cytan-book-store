package com.example.cytanbookstore.service;

import com.example.cytanbookstore.cart.BookDto;
import com.example.cytanbookstore.dao.BookDao;
import com.example.cytanbookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional
    public Book findBook(int id){
        return bookDao.getById(id);
    }

    @Transactional
    public List<BookDto> findAllBook(){
        return bookDao.findAll().stream()
                .map(book -> toDto(book))
                .collect(Collectors.toList());
    }

    @Transactional
    public Book searchedBooks(String name){
        return bookDao.findByName(name);
    }

    @Transactional
    public boolean removeBook(int id){
        if (bookDao.existsById(id)){
            bookDao.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public Book addBook(BookDto bookDto){
        Book book = toEntity(bookDto);
        return bookDao.save(toEntity(bookDto));
    }

    @Transactional
    public Book updateBook(int id,BookDto bookDto){
        Book oldBook = toEntity(bookDto);
        oldBook.setId(id);
        if (bookDao.existsById(id)){
            return bookDao.saveAndFlush(oldBook);
        }else {
            return null;
        }

    }

    private BookDto toDto(Book book){
        return new BookDto(book.getId(),
                book.getName(),
                book.getPublishDate(),
                book.getAuthor(),
                book.getImageUrl(),
                book.getPrice(),
                book.getGenreList());
    }

    private Book toEntity(BookDto bookDto){
        return new Book(
                bookDto.getName(),
                bookDto.getPublishDate(),
                bookDto.getAuthor(),
                bookDto.getImageUrl(),
                bookDto.getPrice(),
                bookDto.getGenreList());
    }

    public List<Book> changeDtoListToBookList(List<BookDto> bookDtoList){
        List<Book> checkOutBookList = new ArrayList<>();
        for (BookDto bookDto : bookDtoList){
            checkOutBookList.add(bookDao.getById(bookDto.getId()));
        }
        return checkOutBookList;
    }

    public List<BookDto> changeBookListToDtoList(List<Book> bookList){
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList){
            bookDtoList.add(toDto(book));
        }
        return bookDtoList;
    }

}
