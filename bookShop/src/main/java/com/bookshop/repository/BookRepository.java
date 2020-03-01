package com.bookshop.repository;

import com.bookshop.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    public Book getBookById(Long id);




    List<Book> findByTitleContaining(String title);

    @Query("select s from Book s where title like %?1%")
    Page<Book> findByTitle(String title, Pageable pageable);


//    Page<Book> findAllBooks(Pageable pageable);

    @Query(value ="SELECT * FROM book ORDER BY date_of_publication DESC LIMIT 4",nativeQuery = true)
    List<Book> bookDateSorted ();

    @Query(value = "SELECT * FROM book ORDER BY sales DESC LIMIT 4",nativeQuery = true)
    List<Book> bookSalesSorted();

//    @Query("SELECT * FROM book By")












//    @Query("select s from Book s where ")
//    public Book getNumberOfAvailableBooks();
}
