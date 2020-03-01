package com.bookshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookshop.entities.User;
import com.bookshop.security.registrationDTO.UserRegistrationDto;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

//    Optional<User> findById(Long id);

}
