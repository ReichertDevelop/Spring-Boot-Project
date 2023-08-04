package com.develop.course.services;

import com.develop.course.entities.Payment;
import com.develop.course.repository.PaymentRepository;
import com.develop.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentRepository repository;

    public Payment insert(Payment obj) {
        return repository.save(obj);
    }

    public Payment update(Long id, Payment obj) {
        try {
            Payment pay = repository.getReferenceById(id);
            updateData(pay, obj);
            return pay;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Payment pay, Payment obj) {
        pay.setMoment(obj.getMoment());
        pay.setOrder(obj.getOrder());
    }
}
