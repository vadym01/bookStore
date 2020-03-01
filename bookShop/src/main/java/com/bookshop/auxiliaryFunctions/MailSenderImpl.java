package com.bookshop.auxiliaryFunctions;

import com.bookshop.entities.Delivery;
import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String address, String subject, User user, UserOrder userOrder, Delivery delivery) throws MessagingException {

        final MimeMessage mail = mailSender.createMimeMessage();
        final MimeMessageHelper helper= new MimeMessageHelper( mail, true );


//        /
        helper.setTo( address );
        helper.setSubject( subject );
        helper.setText( "text/html", "Message from book shop\n" +
                "Hello" + user.getFirstName() + ".Your order has been confirmed:\n" +
                "Information for delivery:\n" + "Receiver:" + user.getFirstName() + user.getLastName() +
                "\n Address:/n" + "Country:" + delivery.getDeliveryCountry() +
                "\nLocation:" + delivery.getDeliveryLocation() +
                "\nStreet:" + delivery.getDeliveryStreet() +
                "\nZipCode:" + delivery.getDeliveryZipCode());
        mailSender.send( mail );






//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        try {
//            helper.setTo(address);
//            helper.setText(text);
//            helper.setSubject(subject);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}
