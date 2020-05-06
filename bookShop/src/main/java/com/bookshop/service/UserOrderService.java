package com.bookshop.service;

import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserOrderService {
    List<UserOrder> getUserOrder(User user,boolean condition);

    Page<UserOrder> getUserOrderAdmin(Pageable pageable,List<UserOrder> userOrders);

    void changeStatusToOrder(Long id);

    void changeStatusToAdminOrder(Long id);

    void changeStatusForOrderAdmin(Long id);

    List<UserOrder> userOrderForAdmin();
}
