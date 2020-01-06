package com.bookshop;


import com.bookshop.entities.User;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;


@SpringBootTest
class BookshopApplicationTests {

	@Autowired
    UserRepository userRepository;




	@Autowired
    UserService userService;
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
//    public void myAccount(Principal principal){
//        User user = userRepository.getOne(userService.gerCurrentRegistredUser());
//        System.out.println(user.getEmail());
//    }

}
