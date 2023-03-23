package com.CaseStudy.dao;

import java.util.List;
import com.CaseStudy.Entities.Order.OrderHistory;
import com.CaseStudy.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Integer> {

    public List<OrderHistory> findAllByUser(User user);
}
