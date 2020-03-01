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
//        deliveryRepository.findByActualAndUser(true,user).forEach(s -> s.setActual(false));
        Delivery delivery1 = deliveryRepository.findByActualAndUser(true,user);
        if( delivery1 != null){
           delivery1.setActual(false);
        };
        delivery.setActual(true);
        deliveryRepository.save(delivery);
    }

    @Override
    public Delivery findByActualAndUser(boolean actual,User user) {
        return deliveryRepository.findByActualAndUser(actual,user);
    }
}
