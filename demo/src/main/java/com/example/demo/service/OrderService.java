package com.example.demo.service;

import com.example.demo.Repository.BurgerRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.entity.Burger;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private BurgerRepository burgerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, BurgerRepository burgerRepository) {
        this.orderRepository = orderRepository;
        this.burgerRepository = burgerRepository;
    }


    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    public void saveOrder(Order order) {
            orderRepository.save(order);


    }
    public void addBurgerToOrder(Order order, Burger burger){
        if(orderRepository.existsByUser(order.getUser()) == false) {
            orderRepository.save(order);
        }else {
            LocalDateTime myDateObj = LocalDateTime.now();

            Order order1 = orderRepository.getByUser(order.getUser());
            order1.addBurger(burger);
            order1.setCreatedAt(myDateObj);
            orderRepository.save(order1);
        }


    }
    public List<Order> findByUserId(User user) {
        return orderRepository.findByUser(user);
    }
}
