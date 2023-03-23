package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Order.OrderHistory;
import com.CaseStudy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/history")
    public List<OrderHistory> getOrderHistory(HttpServletRequest request){
        return orderService.getOrders(request);
    }

    @GetMapping("/place")
    public Cart createOrder(HttpServletRequest request){
        return orderService.createOrder(request);
    }
}
