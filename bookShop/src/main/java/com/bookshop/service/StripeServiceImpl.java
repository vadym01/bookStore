package com.bookshop.service;

import com.bookshop.entities.*;
import com.bookshop.repository.*;
import com.mysql.cj.result.LocalDateTimeValueFactory;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${private_key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    BookService bookService;

    @Autowired
    CartExtensionService cartExtensionService;

    @Autowired
    UserCartRepository userCartRepository;


    @Autowired
    CartExtensionRepository cartExtensionRepository;


    @Autowired
    DeliveryService deliveryService;

    @Override
    public void chargeNewCard(UserOrder userOrder,User user,UserCart userCart) throws AuthenticationException,InvalidRequestException,
            APIConnectionException,CardException,APIException{
        userOrder.setTotalPrice(user.getUserCart().getTotalPrice());
        userOrder.setUser(user);
        userOrder.setDelivery(deliveryService.findByActualAndUser(true,user));
        userOrder.setOrderDate(LocalDateTime.now());
        userOrder.setDisplayToUser(true);
        userOrder.setDisplayToAdmin(true);
        userOrderRepository.save(userOrder);

        List<CartExtensions> cartExtension = cartExtensionService.getCartExtensionsForUserOrder(false,userCart);
        for (CartExtensions extensions:
                cartExtension) {
            CartExtensions cartExtensionsList = extensions;
            cartExtensionsList.setOrderStatus(true);
            cartExtensionsList.setDisplayToUserStory(true);
            cartExtensionsList.setUserOrder(userOrder);
            cartExtensionRepository.save(cartExtensionsList);

            Book book = extensions.getBook();
                book.setSales(book.getSales()+1);
                book.setNumberOfAvailableBooks(book.getNumberOfAvailableBooks() - extensions.getQuantity());
                if(book.getNumberOfAvailableBooks()<=0){
                    book.setAvailable(false);
            }
            userCart.setTotalPrice(0);
            bookService.addNewBook(book);
        }
    }
}
