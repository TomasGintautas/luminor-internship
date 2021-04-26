package com.luminor.internship.repository.dao;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    private UUID id;

    @CreationTimestamp
    private LocalDateTime timestamp;

    private String debtorIban;
    private BigDecimal amount;

    public Payment() {
    }

    public Payment(String debtorIban, BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.debtorIban = debtorIban;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timeStamp) {
        this.timestamp = timeStamp;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", timeStamp=" + timestamp +
                ", debtorIban='" + debtorIban + '\'' +
                ", amount=" + amount +
                '}';
    }
}