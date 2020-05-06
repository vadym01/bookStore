package com.bookshop.service;

import com.bookshop.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addNewBook(Book books);
    Iterable<Book> findAll();
    void deleteBook(Long id);
    List<Book> findAllBoks();
    Book getBook(Long id);
    List <Book> findBook(String book);
    Page<Book> getAllBooksPaginated(Pageable pageable);
    List<Book> sortByDateFirstFour();
    List<Book> sortBySalesFirstFour();
    Page<Book> getAllBooksPaginatedByTitle(String title,Pageable pageable);


}
