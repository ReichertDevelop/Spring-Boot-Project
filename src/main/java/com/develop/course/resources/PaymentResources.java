package com.develop.course.resources;

import com.develop.course.entities.Payment;
import com.develop.course.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResources {

    @Autowired
    PaymentService service;

    @PostMapping
    public ResponseEntity<Payment> insert(@RequestBody Payment obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
