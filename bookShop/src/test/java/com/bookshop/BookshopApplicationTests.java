package com.bookshop;


import com.bookshop.entities.Book;
import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import com.bookshop.repository.*;

import com.bookshop.service.CartExtensionService;
import com.bookshop.service.UserOrderService;
import com.bookshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class BookshopApplicationTests {

	@Autowired
    UserRepository userRepository;

	@Autowired
    UserOrderRepository userOrderRepository;

	@Autowired
	CartExtensionRepository cartExtensionRepository;

    @Autowired
    CartExtensionService cartExtensionService;


    @Autowired
    BookRepository bookRepository;

	@Autowired
    UserService userService;

	@Autowired
	UserOrderService userOrderService;

//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void saveUser() {
//		User user = new User();
//		user.setName("John");
//		user.setLastName("Lincoln");
//		user.setPassword("asfasfasfasf");
//		userRepository.save(user);
//	}
//
//	@Test
//	public void updateUser(){
//		User user = userRepository.findById(1L).get();
//		user.setLastName("ssssss");
//		userRepository.save(user);
//
//
//	}

//    @Test
//    public void myAccount(){
//        PageRequest pageRequest =  PageRequest.of(0,4);
//            bookRepository.findByTitleContaining("s",pageRequest);

//        List<CartExtensions> cartExtensionsLsit = cartExtensionService.cartExtensionsByOrderStatus(true);
//


//    @Test
//    public void myAccount(){
//        Optional<User> user = userRepository.findById(1l);
//        if(user.isPresent()) {
//            User user1 = user.get();
//            List<UserOrder> userOrderList = userOrderRepository.findByUserAndDisplayToUser(user1,true);
//            userOrderList.forEach(s-> s.getCartExtensionsList().toArray());
//        }

//        }



    }

