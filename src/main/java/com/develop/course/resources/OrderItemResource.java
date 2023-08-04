package com.develop.course.resources;

import com.develop.course.entities.OrderItem;
import com.develop.course.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{orderItems}")
public class OrderItemResource {

    @Autowired
    OrderItemService service;

    @PostMapping
    public ResponseEntity<OrderItem> insert(@RequestBody OrderItem obj) {
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
