package com.bookshop.repository;

import com.bookshop.entities.*;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartExtensionRepository extends CrudRepository<CartExtensions,Long> {

    List<CartExtensions> findByOrderStatusAndUserCart(boolean orderStatus, UserCart userCart);

    CartExtensions getCartExtensionsById(Long id);

    List<CartExtensions> findByOrderStatus(boolean orderStatus);

    List<CartExtensions> findByUserOrder(UserOrder UserOrder);


}
