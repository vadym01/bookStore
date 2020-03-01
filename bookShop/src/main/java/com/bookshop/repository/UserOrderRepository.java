package com.bookshop.repository;

import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder,Long> {

    List<UserOrder> findByUserAndDisplayToUser(User user,boolean displayToUser);

    @Query("select s from UserOrder s where s.displayToAdmin=TRUE")
    List<UserOrder> userOrderForAdmin();


}
