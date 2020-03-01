package com.bookshop.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bookshop.entities.*;
import com.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//
import com.bookshop.security.registrationDTO.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;





    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setTelephone(registration.getTelephone());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        UserCart userCart = new UserCart();
        userCart.setUser(user);
        user.setUserCart(userCart);

        return userRepository.save(user);

    }

//    @Override
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }









//    find current registred user
//    @Override
//    public Long getCurrentUserId() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        String id = null;
//        if (authentication != null)
//            if (authentication.getPrincipal() instanceof UserDetails)
//                id = ((UserDetails) authentication.getPrincipal()).getUsername();
//            else if (authentication.getPrincipal() instanceof String)
//                id = (String) authentication.getPrincipal();
//        try {
//            return Long.valueOf(id != null ? id : "1"); //anonymoususer
//        } catch (NumberFormatException e) {
//            return 1L;
//        }
//
//    }
//
//    @Override
//    public Long gerCurrentRegistredUser() {
//        return getCurrentUserId();
//    }



}
