package com.bookshop.auxiliaryFunctions;

import com.bookshop.entities.Delivery;
import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;

import javax.mail.MessagingException;

public interface MailSender {
    void sendMail(String address, String subject, User user, UserOrder userOrder, Delivery delivery) throws MessagingException;
}
