package com.payment_service.repository;

import com.payment_service.entity.Payement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payement,Integer> {
    Optional<Payement> findByItem(String item);
}
