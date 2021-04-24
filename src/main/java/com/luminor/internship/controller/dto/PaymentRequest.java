package com.luminor.internship.controller.dto;

import java.math.BigDecimal;

public class PaymentRequest {

    private BigDecimal amount;
    private String debtorIban;

    public PaymentRequest() {
    }

    public PaymentRequest(BigDecimal amount, String debtorIban) {
        this.amount = amount;
        this.debtorIban = debtorIban;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }
}