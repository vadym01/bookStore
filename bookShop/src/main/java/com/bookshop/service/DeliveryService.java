package com.bookshop.service;

import com.bookshop.entities.Delivery;
import com.bookshop.entities.User;

public interface DeliveryService {
    void saveDeliveryAddress(User user,Delivery delivery);

    Delivery findByActualAndUser(boolean actual,User user);
}
