package com.bookshop.service;

import com.bookshop.entities.*;
import com.bookshop.repository.CartExtensionRepository;
import com.bookshop.repository.UserCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartExtensionServiceImpl implements CartExtensionService{

    @Autowired
    CartExtensionRepository cartExtensionRepository;

    @Autowired
    UserCartRepository userCartRepository;
//
//    @Autowired
//    AdminUsersOrdersService adminUsersOrdersService;

    @Override
    public void updateCartExtension(CartExtensions cartExtensions) {
        double total =0;
        total += cartExtensions.getTotalPrice();
        cartExtensions.setTotalPrice(total);
        cartExtensionRepository.save(cartExtensions);
    }

    @Override
    public void saveItem(Book book, User user,CartExtensions cartExtensions) {
        cartExtensions.setUser(user);
        cartExtensions.setTotalPrice(book.getPrice());
        cartExtensions.setBook(book);
        cartExtensions.setUser(user);
        cartExtensions.setUserCart(user.getUserCart());
        cartExtensions.setDisplayToUserStory(false);
        cartExtensions.setOrderStatus(false);
//        adminUsersOrdersService.saveOrderForAdmin(user,book);

        cartExtensionRepository.save(cartExtensions);
    }

    @Override
    public void deleteItemById(Long id) {
        cartExtensionRepository.deleteById(id);
    }



    @Override
        public List<CartExtensions> getCartExtensionsForUserOrder(boolean status,UserCart userCart) {
//       List<CartExtensions> cartExtensionServicesList = cartExtensionRepository.findByOrderStatusAndUserCart(status,userCart);
        return cartExtensionRepository.findByOrderStatusAndUserCart(status,userCart);
    }

    @Override
    public List<CartExtensions> cartExtensionsByOrderStatus(boolean status) {
        return cartExtensionRepository.findByOrderStatus(status);
    }

    @Override
    public List<CartExtensions> findByUserOrder(UserOrder userOrder) {
        return cartExtensionRepository.findByUserOrder(userOrder);
    }


}
