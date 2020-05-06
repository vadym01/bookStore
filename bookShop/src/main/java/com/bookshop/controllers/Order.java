package com.bookshop.controllers;

//import com.bookshop.auxiliaryFunctions.MailSender;
import com.bookshop.entities.*;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.CartExtensionRepository;
import com.bookshop.repository.DeliveryRepository;
import com.bookshop.service.*;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class Order {

    @Autowired
    UserService userService;

    @Autowired
    StripeService stripeService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartExtensionService cartExtensionService;

    @Autowired
    CartExtensionRepository cartExtensionRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryService deliveryService;

//    @Autowired
//    MailSender mailSender;


    @Value("${publishable_key}")
    private String stripeKey;

    @RequestMapping("/orderInformation")
    public String orderInformation(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
//        BankCard bankCard = user.getUserBankCard();
//        Delivery delivery = user.getDelivery();
        UserCart userCart = user.getUserCart();

        if(deliveryService.findByActualAndUser(true,user) == null){
            Delivery delivery = new Delivery();
            model.addAttribute("delivery",delivery);
            model.addAttribute("fieldDisabled","true");

        }else{
            Delivery delivery = deliveryService.findByActualAndUser(true,user);
            model.addAttribute("delivery",delivery);
            model.addAttribute("fieldDisabled","false");
        }

        model.addAttribute("userCart",userCart);
        model.addAttribute("cart",userCart);


        List<CartExtensions> itemsForUserCart = cartExtensionService.getCartExtensionsForUserOrder(false,userCart);
        model.addAttribute("cartExtension",itemsForUserCart);

        double totalPrice = user.getUserCart().getTotalPrice() * 100;
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("stripeKey", stripeKey);

        return "orderInformation";
    }

    @RequestMapping(value = "/saveNewDeliveryInfOrder", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery")Delivery delivery,Principal principal){
        User user = userService.findByEmail(principal.getName());
        deliveryService.saveDeliveryAddress(user,delivery);
        return "redirect:orderInformation";
    }



    @RequestMapping(value = "/paymentProcess", method = RequestMethod.POST)
    public String paymentProcess(@ModelAttribute("userOrder")UserOrder userOrder,Principal principal,Model model) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        User user = userService.findByEmail(principal.getName());
        Delivery delivery = deliveryService.findByActualAndUser(true,user);
//        try {
//            mailSender.sendMail(user.getEmail(),"bookStore",user,userOrder,delivery);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        UserCart userCart = user.getUserCart();
        stripeService.chargeNewCard (userOrder,user,userCart);
        List<CartExtensions> cartExtensionsList = cartExtensionService.findByUserOrder(userOrder);
        model.addAttribute("cartExtensions",cartExtensionsList);
        model.addAttribute("userOrder",userOrder);
        return "paymentResult";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "paymentResult";
    }


    }



