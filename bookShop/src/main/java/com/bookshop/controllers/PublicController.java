package com.bookshop.controllers;

import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @RequestMapping("/allBooks")
    public String findAll(ModelMap model){
        List<Book> allBooks = bookService.findAllBoks();
        model.addAttribute("bookList",allBooks);
        return "allBooks";
    }



    @RequestMapping("/bookPage")
    public String bookDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if(principal != null) {
            String email = principal.getName();
            User user = userService.findByEmail(email);
            model.addAttribute("user", user);
        }

        Book book = bookService.getBook(id);
        model.addAttribute("book", book);


        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,10);
        model.addAttribute("qtyList",qtyList);
        return "bookPage";
    }



}
