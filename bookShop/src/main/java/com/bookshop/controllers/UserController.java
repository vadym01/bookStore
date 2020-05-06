package com.bookshop.controllers;

import com.bookshop.entities.*;
import com.bookshop.repository.CartExtensionRepository;
import com.bookshop.repository.DeliveryRepository;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;
    @Autowired
    UserRepository repository;


    @Autowired
    DeliveryService deliveryService;

    @Autowired
    BookService bookService;

    @Autowired
    CartExtensionService cartExtensionService;

    @Autowired
    UserCartService userCartService;


    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    CartExtensionRepository cartExtensionRepository;

    @Autowired
    UserOrderService userOrderService;


    @RequestMapping("/myAccount")
    public String myAccountPage(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());


        if(deliveryService.findByActualAndUser(true,user) == null){
            Delivery delivery = new Delivery();
            model.addAttribute("delivery",delivery);
        }else{
            Delivery delivery = deliveryService.findByActualAndUser(true,user);
            model.addAttribute("delivery",delivery);
        }
        UserCart userCart = user.getUserCart();

        model.addAttribute("user", user);
        model.addAttribute("userCart",userCart);
        model.addAttribute("cart",userCart);

                List<CartExtensions> itemsForUserCart = cartExtensionService.getCartExtensionsForUserOrder(false,userCart);
                model.addAttribute("cartExtensionsCart",itemsForUserCart);
                if (itemsForUserCart.isEmpty()){
                    model.addAttribute("empty","true");
                }

                List<UserOrder> userOrderList = userOrderService.getUserOrder(user,true);
                model.addAttribute("userOrderStory",userOrderList);
                if(userOrderList.isEmpty()){
                    model.addAttribute("userOrderStoryEmpty","true");
                }
                else {
                    model.addAttribute("userOrderStoryEmpty","false");
                }

        return "myAccount";
    }


    @RequestMapping(value = "/saveNewDeliveryInf", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery")Delivery delivery,Principal principal){
        User user = userService.findByEmail(principal.getName());
        deliveryService.saveDeliveryAddress(user,delivery);
        return "redirect:myAccount";
    }

    @RequestMapping(value = "/testStory")
    public String testStory(Model model,Principal principal){
        User user = userService.findByEmail(principal.getName());


        if(deliveryService.findByActualAndUser(true,user) ==null){
            Delivery delivery = new Delivery();
            model.addAttribute("delivery",delivery);
        }else{
            Delivery delivery = deliveryService.findByActualAndUser(true,user);
            model.addAttribute("delivery",delivery);
        }
        UserCart userCart = user.getUserCart();

        model.addAttribute("user", user);
        model.addAttribute("userCart",userCart);
        model.addAttribute("cart",userCart);

        List<CartExtensions> itemsForUserCart = cartExtensionService.getCartExtensionsForUserOrder(false,userCart);
        model.addAttribute("cartExtensionsCart",itemsForUserCart);
        if (itemsForUserCart.isEmpty()){
            model.addAttribute("empty","true");
        }

        List<UserOrder> userOrderList = userOrderService.getUserOrder(user,true);
        model.addAttribute("userOrderStory",userOrderList);
        if(userOrderList.isEmpty()){
            model.addAttribute("userOrderStoryEmpty","true");
        }
        else {
            model.addAttribute("userOrderStoryEmpty","false");
        }
        return "testStory";
    }

    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    public String addToCart(@RequestParam("id")Long id, Principal principal,CartExtensions cartExtensions,Model model){
        model.addAttribute("statusSuccess","OK");
        Book book = bookService.getBook(id);
        User user = userService.findByEmail(principal.getName());
        UserCart userCart = user.getUserCart();
        cartExtensionService.saveItem(book,user,cartExtensions);
        userCartService.updateUserShoppingCart(userCart,user);
        return "redirect:bookPage?id=" + id;

    }

    @RequestMapping("/changeDisplayStatus")
    public String change(@RequestParam("id")Long id){
        userOrderService.changeStatusToOrder(id);

        return "redirect:myAccount";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam("id")Long id,Principal principal){
        User user = userService.findByEmail(principal.getName());
        cartExtensionService.deleteItemById(id);
        UserCart userCart = user.getUserCart();
        userCartService.updateUserShoppingCart(userCart,user);
        return"redirect:myAccount";
    }

}
