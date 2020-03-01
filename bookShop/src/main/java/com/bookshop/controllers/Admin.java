package com.bookshop.controllers;

import com.bookshop.entities.Book;
import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import com.bookshop.repository.BookRepository;
//import com.bookshop.service.AdminUsersOrdersService;
import com.bookshop.repository.CartExtensionRepository;
import com.bookshop.repository.UserOrderRepository;
import com.bookshop.service.BookService;

import com.bookshop.service.CartExtensionService;
import com.bookshop.service.UserOrderService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/admin")
public class Admin {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartExtensionService cartExtensionService;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private UserService userService;






    @RequestMapping("/userOrders")
    public String searchAll(@RequestParam("size") Optional<Integer> size,
                            @RequestParam("page")Optional<Integer>page,Model model){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);
        Page<UserOrder> userOrderPage = userOrderService.getUserOrderAdmin(PageRequest.of(currentPage-1,pageSize),userOrderService.userOrderForAdmin());
        model.addAttribute("userOrder",userOrderPage);
        int totalPages = userOrderPage.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "adminPage";
    }









    @RequestMapping("/changeAdminStatus")
    public String change(@RequestParam("id")Long id){
        userOrderService.changeStatusToAdminOrder(id);
        return "redirect:userOrders";
    }

    @RequestMapping("/changeOrderStatus")
    public String changeOrderStatus(@RequestParam("id")Long id){
        userOrderService.changeStatusForOrderAdmin(id);
        return "redirect:userOrders";
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
        public String bookList(@RequestParam("size") Optional<Integer> size,
                @RequestParam("page")Optional<Integer>page,Model model){
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(6);
            Page<Book> bookList = bookService.getAllBooksPaginated(PageRequest.of(currentPage-1,pageSize));
            model.addAttribute("bookList",bookList);
            int totalPages = bookList.getTotalPages();
            if(totalPages > 0){
                List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers",pageNumbers);
            }
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
