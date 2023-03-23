package com.CaseStudy.Service;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Order.OrderHistory;
import com.CaseStudy.Entities.User.User;
import com.CaseStudy.dao.CartRepository;
import com.CaseStudy.dao.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private HelperService helperService;

    @Autowired
    private CartRepository cartRepository;

    public List<OrderHistory> getOrders(HttpServletRequest request) {
        User user = helperService.fetchUserFromToken(request);
        return orderHistoryRepository.findAllByUser(user);
    }

    public Cart createOrder(HttpServletRequest request){
        User user = helperService.fetchUserFromToken(request);
        Cart cart = cartRepository.findByUserId(user.getUserId());
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderDate(new Date());
        List<CartItem> orderItems = new ArrayList<>();
        orderItems.addAll(cart.getCartItems());
        orderHistory.setCartItems(orderItems);
        orderHistory.setUser(user);
        System.out.println(orderHistory);
        OrderHistory result = orderHistoryRepository.save(orderHistory);
        cart.getCartItems().clear();
        Cart change = cartRepository.save(cart);
        return change;

    }
}
