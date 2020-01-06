package com.bookshop.service;

import com.bookshop.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
   public Book addNewBook(Book books);
    public Iterable<Book> findAll();
    public void deleteBook(Long id);
//    Book updateBook(Book book);
////    List<Book> getAllBooks(Long id);
//    List <Book> getAll();
    public List<Book> findAllBoks();
//   public Optional <Book> findOne(Long id);
    public Book getBook(Long id);
//    public Book updateBook(Long id);
}
