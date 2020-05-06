package com.bookshop.service;

import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import com.bookshop.entities.UserCart;

import java.util.Optional;

public interface UserCartService {
    void updateUserShoppingCart(UserCart userCart,User user);

}
