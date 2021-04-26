package com.luminor.internship.repository;


import com.luminor.internship.repository.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    default List<Payment> getPayments(){
        return this.findAll();
    }

    @Query("SELECT p FROM Payment p WHERE p.debtorIban = ?1")
    Optional<Payment> findByDebtorIban(String debtorIban);
}