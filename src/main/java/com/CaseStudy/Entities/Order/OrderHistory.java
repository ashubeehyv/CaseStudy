package com.CaseStudy.Entities.Order;

import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    private Date orderDate;

    @OneToMany(cascade= CascadeType.ALL)
    private List<CartItem> cartItems;

    @JsonIgnore
    @ManyToOne
    private User user;

    public OrderHistory() {
    }

    public OrderHistory(int orderId, Date orderDate, List<CartItem> cartItems, User user) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cartItems = cartItems;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", cartItems=" + cartItems +
                ", user=" + user +
                '}';
    }
}
