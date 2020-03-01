package com.bookshop.entities;

import com.bookshop.entities.Book;
import com.bookshop.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class CartExtensions {

//    for Addition detail

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;
    private int quantity;
    private boolean displayToUserStory;
    private boolean orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    private UserCart userCart;

    @ManyToOne
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_order")
    private UserOrder userOrder;


    public CartExtensions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart userCart) {
        this.userCart = userCart;
    }


    public boolean isDisplayToUserStory() {
        return displayToUserStory;
    }

    public void setDisplayToUserStory(boolean displayToUserStory) {
        this.displayToUserStory = displayToUserStory;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }


    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
