package com.bookshop.service;

import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import com.bookshop.entities.UserCart;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CartExtensionRepository;
import com.bookshop.repository.UserCartRepository;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCartServiceImpl implements UserCartService {

    @Autowired
    UserCartRepository userCartRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartExtensionService cartExtensionService;


    @Autowired
    CartExtensionRepository cartExtensionRepository;



    @Override
    public void updateUserShoppingCart(UserCart userCart,User user) {
        double total = 0;
//        List<CartExtensions> cartExtensions = user.getCartExtensionsList();
        List<CartExtensions> cartExtensions = cartExtensionService.getCartExtensionsForUserOrder(false,userCart);
        for(CartExtensions extensions : cartExtensions){
                cartExtensionService.updateCartExtension(extensions);
                total += extensions.getBook().getPrice()*extensions.getQuantity();
        }
        userCart.setTotalPrice(total);
        userCartRepository.save(userCart);
    }



//

//    @Override
//    public void saveItem(Book book, User user,UserCart userCart, int quantity,CartExtensions cartExtensions) {
//        double temp = cartExtensions.getTotalPrice() + book.getPrice();
//        cartExtensions.setBook(book);
//        cartExtensions.setUser(user);
//        cartExtensions.setTotalPrice(temp);
//        cartExtensionRepository.save(cartExtensions);
//        cartExtensionRepository.count(Example.of(cartExtensions));
//        userCart.setQuantity(quantity);
//        userCart.setUser(user);
//        userCart.setBook(book);
//        userCartRepository.save(userCart);
//    }

//    @Override
//    public void delItemCart(Long id) {
//        userCartRepository.deleteById(id);
//    }

//    @Override
//    public void setUser(User user) {
//        UserCart userCart = new UserCart();
//        userCart.setUser(user);
//        userCartRepository.save(userCart);
//    }

//    @Override
//    public Optional<UserCart> findById(Long id) {
//        return userCartRepository.findById(id);
//    }


}
