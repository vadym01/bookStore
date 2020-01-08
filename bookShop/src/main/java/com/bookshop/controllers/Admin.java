package com.bookshop.controllers;

import com.bookshop.entities.Book;
import com.bookshop.repository.BookRepository;
import com.bookshop.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class Admin {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String adminPage(){
        return "adminPage";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String book(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "newBook";
    }

    @RequestMapping( value = "/addBook", method = RequestMethod.POST)
    public String saveNewBook(@ModelAttribute("book")Book book,HttpServletRequest request){

        bookService.addNewBook(book);

            MultipartFile bookImage = book.getImage();
        try {
            byte [] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/bookImage/"+name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:bookList";
    }

    @RequestMapping( value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute("book")Book book,HttpServletRequest request){

        bookService.addNewBook(book);

        MultipartFile bookImage = book.getImage();
        try {
            byte [] bytes = bookImage.getBytes();
            String id = book.getId() + ".png";
            Files.deleteIfExists(Paths.get("src/main/resources/static/bookImage/"+id));
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/bookImage/"+id)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:bookList";
    }

    @RequestMapping("/bookList")
    public String bookList(Model model){
        Iterable<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        return "bookList";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam("id")Long id, ModelMap modelMap){
        Book deleteBook = bookService.getBook(id);
        String imageId = deleteBook.getId() + ".png";
        try {
            Files.delete(Paths.get("src/main/resources/static/bookImage/"+imageId));
        }catch (Exception e){
            e.printStackTrace();
        }
        bookService.deleteBook(id);
        return "redirect:/admin/bookList";
    }


    @RequestMapping("bookList/bookInfo")
    public String updateBook(@RequestParam("id")Long id, Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book",book);
        return "updateBook";
    }


}
