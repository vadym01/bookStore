package com.bookshop.controllers;

import com.bookshop.entities.*;
import com.bookshop.repository.UserRepository;
import com.bookshop.service.BankCardService;
import com.bookshop.service.DeliveryService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository repository;

    @Autowired
    BankCardService bankCardService;

    @Autowired
    DeliveryService deliveryService;

    @RequestMapping("/myAccount")
    public String myAccountPage(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        BankCard bankCard = new BankCard();
        Delivery delivery = new Delivery();

        model.addAttribute("bankCard",bankCard);
        model.addAttribute("user", user);
        model.addAttribute("delivery", delivery);

        Iterable<BankCard> bankCards = user.getUserBankCardList();
        model.addAttribute("bankCardList",bankCards);

        Iterable<Delivery> deliveryInformation = user.getUserDeliveryList();
        model.addAttribute("userDeliveryList",deliveryInformation);

        return "myAccount";
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST)
    public String saveCard(@ModelAttribute("bankCard")BankCard bankCard,Principal principal){
        User user = userService.findByEmail(principal.getName());
        bankCard.setDefaultPayment(false);
        bankCardService.saveCard(user,bankCard);
        return "redirect:myAccount";
    }

    @RequestMapping(value = "/saveNewDeliveryInf", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery")Delivery delivery,Principal principal){
        User user = userService.findByEmail(principal.getName());
        deliveryService.saveDeliveryAddress(user,delivery);
        return "redirect:myAccount";
    }








//    controlandDeletebecoseIDontNeedIt
//    @RequestMapping(value = "/allBankCards")
//    public String allBankCards(Model model,Principal principal){
//        User user = userService.findByEmail(principal.getName());
//        return "myAccount";
//    }

//    @RequestMapping("/myBankCards")
//    public String allSavedCards(){
//        bankCardService.getAll();
//        return
//    }

//    @RequestMapping(value = "/newBankCard", method = RequestMethod.GET)
//    public String addNewBook(Model model) {
//        BankCard bankCard = new BankCard();
//        model.addAttribute("bankCard", bankCard);
//        return "testmyAccount";
//    }
//
//    @RequestMapping(value = "/newBankCard", method = RequestMethod.POST)
//    public String updateBook(@ModelAttribute("bankCard") BankCard bankCard, HttpServletRequest request) {
//
//        bankCardService.saveCard(bankCard);
//
//        return "testmyAccount";
//    }




}
