package com.bookshop.controllers;

import com.bookshop.entities.Book;
import com.bookshop.repository.BookRepository;
import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class FrontPageController {


    @Autowired
    BookService bookService;


    @Autowired
    BookRepository bookRepository;


    @GetMapping("/")
    public String root(Model model) {
        List<Book> bookListByDate = bookService.sortByDateFirstFour();
        model.addAttribute("booksByDate",bookListByDate);

        List<Book> bookListBySales = bookService.sortBySalesFirstFour();
        model.addAttribute("booksBySales",bookListBySales);
        return "index";
    }

    @GetMapping("/category/")
    public String getByCategory(@RequestParam(required = false) String category,
                                @RequestParam(required = false,defaultValue = "12")Integer size,
                                @RequestParam(required = false,defaultValue = "1") Integer page,
                                Model model){
        model.addAttribute("category",category);
//        Page<Book> bookPage = bookService.getAllBooksPaginated(PageRequest.of(page-1,quantity,Sort.by(category).descending()));
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(page-1,size,Sort.by(category).descending()));
        model.addAttribute("bookPage",bookPage);
        int totalPages = bookPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "booksCategory";
    }

}
