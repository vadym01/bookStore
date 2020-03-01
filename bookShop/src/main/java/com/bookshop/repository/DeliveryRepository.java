package com.bookshop.repository;

import com.bookshop.entities.Book;
import com.bookshop.entities.Delivery;
import com.bookshop.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    Delivery findByActualAndUser(boolean actual, User user);
}
