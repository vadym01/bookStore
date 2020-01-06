package com.bookshop.repository;

import com.bookshop.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
    public Book getBookById(Long id);
//    @Query("select s from Book s where ")
//    public Book getNumberOfAvailableBooks();
}
