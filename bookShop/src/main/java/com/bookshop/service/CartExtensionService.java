package com.bookshop.service;

import com.bookshop.entities.*;

import java.util.List;

public interface CartExtensionService {

    void updateCartExtension(CartExtensions cartExtensions);
    void saveItem(Book book, User user, CartExtensions cartExtensions);

    void deleteItemById(Long id);

    List<CartExtensions> getCartExtensionsForUserOrder(boolean status,UserCart userCart);

    List<CartExtensions> cartExtensionsByOrderStatus(boolean status);

    List<CartExtensions> findByUserOrder(UserOrder userOrder);

}
