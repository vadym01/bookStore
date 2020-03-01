package com.bookshop.service;

import com.bookshop.entities.Book;
import com.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{



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
//    public List<Book> findBookByTitle(String title) {
//        return bookRepository.findByTitleContaining(title);
//    }

//    osnov
    @Override
    public List<Book> findBook(String book) {
        return bookRepository.findByTitleContaining(book);
    }



    public Page<Book> getAllBooksPaginated(Pageable pageable) {
        List<Book> books = bookRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Book> bookPage
                = new PageImpl<Book>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }






    public Page<Book> getAllBooksPaginatedByTitle(String title,Pageable pageable) {
        List<Book> books = bookRepository.findByTitleContaining(title);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        Page<Book> bookPage
                = new PageImpl<Book>(list, PageRequest.of(currentPage, pageSize), books.size());

        return bookPage;
    }











    @Override
    public List<Book> sortByDateFirstFour() {
        return bookRepository.bookDateSorted();
    }

    @Override
    public List<Book> sortBySalesFirstFour() {
        return bookRepository.bookSalesSorted();
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
