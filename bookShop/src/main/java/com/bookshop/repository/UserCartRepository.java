package com.bookshop.repository;

import com.bookshop.entities.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCartRepository extends JpaRepository<UserCart,Long> {
}
