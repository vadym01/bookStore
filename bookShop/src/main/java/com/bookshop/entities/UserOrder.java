package com.bookshop.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;
    private boolean displayToUser;
    private boolean displayToAdmin;
    private boolean orderStatus;
    private LocalDateTime orderDate;


    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userOrder",cascade = CascadeType.ALL)
    private List<CartExtensions> cartExtensionsList;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartExtensions> getCartExtensionsList() {
        return cartExtensionsList;
    }

    public void setCartExtensionsList(List<CartExtensions> cartExtensionsList) {
        this.cartExtensionsList = cartExtensionsList;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isDisplayToUser() {
        return displayToUser;
    }

    public void setDisplayToUser(boolean displayToUser) {
        this.displayToUser = displayToUser;
    }

    public boolean isDisplayToAdmin() {
        return displayToAdmin;
    }

    public void setDisplayToAdmin(boolean displayToAdmin) {
        this.displayToAdmin = displayToAdmin;
    }
}
