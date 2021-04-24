package com.luminor.internship.controller.dto;

import com.luminor.internship.repository.dao.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public final class PaymentBuilder {

    private UUID id;
    private LocalDateTime timestamp;
    private String debtorIban;
    private BigDecimal amount;


    private PaymentBuilder() {
    }

    public static PaymentBuilder paymentBuilder() {
        return new PaymentBuilder();
    }

    public PaymentBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PaymentBuilder withDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
        return this;
    }

    public PaymentBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Payment build() {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setTimeStamp(timestamp);
        payment.setDebtorIban(debtorIban);
        payment.setAmount(amount);
        return payment;
    }
}