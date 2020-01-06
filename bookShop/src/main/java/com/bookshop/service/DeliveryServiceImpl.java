package com.bookshop.service;

import com.bookshop.entities.Delivery;
import com.bookshop.entities.User;
import com.bookshop.repository.DeliveryRepository;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void saveDeliveryAddress(User user, Delivery delivery) {
        delivery.setUser(user);
        deliveryRepository.save(delivery);
    }
}
