package com.bookshop.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String password;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<UserCart> userCartsList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<UserOrder> userOrderList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<CartExtensions> cartExtensionsList;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private UserCart userCart;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName,String telephone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email,String telephone, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public List<UserCart> getUserCartsList() {
        return userCartsList;
    }

    public void setUserCartsList(List<UserCart> userCartsList) {
        this.userCartsList = userCartsList;
    }

    public List<UserOrder> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<UserOrder> userOrderList) {
        this.userOrderList = userOrderList;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart userCart) {
        this.userCart = userCart;
    }

    public List<CartExtensions> getCartExtensionsList() {
        return cartExtensionsList;
    }

    public void setCartExtensionsList(List<CartExtensions> cartExtensionsList) {
        this.cartExtensionsList = cartExtensionsList;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", roles=" + roles +
                '}';
    }
}
