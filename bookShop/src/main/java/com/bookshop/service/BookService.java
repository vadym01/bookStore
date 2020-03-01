package com.bookshop.service;

import com.bookshop.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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


//osnov
    List <Book> findBook(String book);


    Page<Book> getAllBooksPaginated(Pageable pageable);

//    List<Book> findBookByTitle(String title);

    List<Book> sortByDateFirstFour();

    List<Book> sortBySalesFirstFour();


    Page<Book> getAllBooksPaginatedByTitle(String title,Pageable pageable);


}
