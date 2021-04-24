package com.luminor.internship.repository;


import com.luminor.internship.repository.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    default List<Payment> getPayments(){
        return this.findAll();
    }

    default void savePayments(Payment payment){
        this.save(payment);
    }
}