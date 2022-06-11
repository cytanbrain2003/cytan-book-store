package com.example.cytanbookstore.dao;

import com.example.cytanbookstore.entities.Book;
import com.example.cytanbookstore.entities.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBookDao extends JpaRepository<UserBook,Integer> {

    @Query("select ub.bookList from UserBook  ub where ub.user.id=:userId")
    List<Book> findUserBookByUserId(@Param("userId")int userId);
}
