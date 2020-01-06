package com.bookshop.service;

import com.bookshop.entities.Book;
import com.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    public BookServiceImpl() {
    }

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }



    @Override
    public Iterable<Book> findAll() {
        Iterable<Book> allBooks= bookRepository.findAll();
        return allBooks;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllBoks() {
         return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.getBookById(id);
    }





//    @Override
//    public Book updateBook(Long id) {
//        return bookRepository.findById(id);
//    }


//
//    @Override
//    public Book updateBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    @Override
//    public List<Book> getAll() {
////        Book book = new Book();
//        List <Book> book = (List<Book>) bookRepository.findAll();
//        return book;
//    }

//    @Override
//    public List<Book> getAllBooks(Long id) {
//        return (List<Book>) bookRepository.findAllById(Collections.singleton(id));
//    }




//    @Override
//    public Optional updateBook(Book book) {
//
//        return bookRepository(book);
//    }

}
