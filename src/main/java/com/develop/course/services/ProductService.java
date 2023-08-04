package com.develop.course.services;

import com.develop.course.entities.Product;
import com.develop.course.repository.ProductRepository;
import com.develop.course.services.exceptions.DatabaseException;
import com.develop.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product insert(Product obj) {
       return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
        throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product obj) {
        try {
            Product prod = repository.getReferenceById(id);
            updateData(obj, prod);
            return prod;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product obj, Product prod) {
        prod.setName(obj.getName());
        prod.setPrice(obj.getPrice());
        prod.setDescription(obj.getDescription());
        prod.setImgUrl(obj.getImgUrl());
    }

}
