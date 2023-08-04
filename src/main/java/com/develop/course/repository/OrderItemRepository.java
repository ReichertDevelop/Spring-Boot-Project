package com.develop.course.repository;

import com.develop.course.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// A inteface repository deve estender do "JpaRepository" os tipos são a classe entidade e o tipo da chave
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
