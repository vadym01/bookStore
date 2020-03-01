package com.bookshop.controllers;

import com.bookshop.entities.Book;
import com.bookshop.repository.BookRepository;
import com.bookshop.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SearchController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;



    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);



//    @RequestMapping("/book/{title}{size}{page}")
//    public String searchAll(@RequestParam("title") String title,@PathVariable("title") String titleval, @PathVariable Map<String,String> pages, Model model){
//
//        model.addAttribute("title",title);
//        int page = Integer.parseInt(pages.get("page"));
//        int size = Integer.parseInt(pages.get("size"));
//        Page<Book> bookSearchList = bookService.getAllBooksPaginatedByTitle(titleval,PageRequest.of(1,1));
//
//        model.addAttribute("bookPage",bookSearchList);
//        int totalPages = bookSearchList.getTotalPages();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers",pageNumbers);
//        }
//        return "searchBook";
//    }








    @RequestMapping("/findBook")
    public String searchAll(@RequestParam String title,
                             @RequestParam(required = false,defaultValue= "1") Integer page,
                             @RequestParam(required = false,defaultValue = "12") Integer size,
                             Model model){
            Page<Book> bookSearchList = bookService.getAllBooksPaginatedByTitle(title,PageRequest.of(page-1,size));
            model.addAttribute("searchBookList",bookSearchList);
            model.addAttribute("bookPage",bookSearchList);
        int totalPages = bookSearchList.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        model.addAttribute("titleVal",title);
        return "searchBook";
    }











//    @RequestMapping("/findBook")
//    public String searchAll(@RequestParam(required = false,defaultValue = "")String title,@PathVariable(required = false) Map<String,String> pages, Model model){
//        List<Book> bookList = bookRepository.findByTitleContaining(title);
//        model.addAttribute("searchBookList",bookList);
//        return "searchBook";
//    }


//













//    @RequestMapping("/findBook/{page}/{size}/{title}")
//    public String searchAll(@PathVariable String title, @PathVariable Map<String,String> pages, Model model){
//        int page = Integer.parseInt(pages.get("page"));
//        int size = Integer.parseInt(pages.get("size"));
//        Page<Book> bookSearchList = bookService.getAllBooksPaginatedByTitle(title,PageRequest.of(1,1));
//
//        model.addAttribute("bookPage",bookSearchList);
//        int totalPages = bookSearchList.getTotalPages();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers",pageNumbers);
//        }
//        return "searchBook";
//    }


//    @RequestMapping("/allBooks")
//    public String searchAll(@RequestParam("size") Optional<Integer> size,
//                            @RequestParam("page")Optional<Integer>page,Model model){
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(3);
//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage-1,pageSize));
//
//
//        model.addAttribute("bookList",bookPage);
//        model.addAttribute("pageSize",pageSize);
//
//        int totalPages = bookPage.getTotalPages();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("totalPages",pageNumbers);
//        }
//        return "allBooks";
//    }

    @RequestMapping("/allBooks")
    public String searchAll(@RequestParam("size") Optional<Integer> size,
                            @RequestParam("page")Optional<Integer>page,Model model){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        Page<Book> bookPage = bookService.getAllBooksPaginated(PageRequest.of(currentPage-1,pageSize));

        model.addAttribute("bookPage",bookPage);
        int totalPages = bookPage.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "allBooks";
    }















//osnov
//    @RequestMapping("/allBooks")
//    public String findAll( @RequestParam("size") Optional<Integer> size,
//                            @RequestParam("page")Optional<Integer>page,Model model){
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(1);
//        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage-1,pageSize));
//        model.addAttribute("bookList",bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("totalPages",pageNumbers);
//        }
//        return "allBooks";
//    }


//    @RequestMapping("/findBook")
//    public String searchAll(@RequestParam(defaultValue = "")String title, @RequestParam("size") Optional<Integer> size,
//                            @RequestParam("page")Optional<Integer>page,Model model){
//
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(1);
////        PageRequest pageRequest = PageRequest.of(currentPage-1,pageSize);
//        List<Book> bookList = bookRepository.findByTitle(title,z.of(currentPage,pageSize, Sort.Direction.DESC,"title"));
//
//
//        model.addAttribute("searchBookList",bookList);
//
//        int totalPages = bookList.size();
//        if(totalPages > 0){
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers",pageNumbers);
//        }
//
//
//
//        return "searchBook";
//    }


}
