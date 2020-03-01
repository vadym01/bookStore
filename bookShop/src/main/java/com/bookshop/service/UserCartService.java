package com.bookshop.service;

import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import com.bookshop.entities.UserCart;

import java.util.Optional;

public interface UserCartService {

//    void saveItem(Book book, User user,UserCart userCart, int quantity, CartExtensions cartExtensions);
//    void delItemCart(Long id);
//
//    void setUser(User user);
//
//    Optional<UserCart> findById(Long id);


//
    void updateUserShoppingCart(UserCart userCart,User user);

}
