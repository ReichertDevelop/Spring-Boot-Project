package com.develop.course.services;

import com.develop.course.entities.OrderItem;
import com.develop.course.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    OrderItemRepository repository;

    public OrderItem insert(OrderItem obj) {
        return repository.save(obj);
    }
}
