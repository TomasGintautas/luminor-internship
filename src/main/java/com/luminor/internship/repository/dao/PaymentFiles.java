package com.luminor.internship.repository.dao;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public class PaymentFiles {

    @CsvBindByName
    private String debtorIban;

    @CsvBindByName
    private BigDecimal amount;

    public PaymentFiles(String debtorIban, BigDecimal amount) {
        this.debtorIban = debtorIban;
        this.amount = amount;
    }

    public PaymentFiles() {
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
}