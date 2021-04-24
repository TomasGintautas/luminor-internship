package com.luminor.internship.service;

import com.luminor.internship.repository.PaymentRepository;
import com.luminor.internship.repository.dao.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentDao;

    @Autowired
    public PaymentService(PaymentRepository paymentDao) {
        this.paymentDao = paymentDao;
    }

    public List<Payment> getPayments(){
        return paymentDao.getPayments().stream()
                .map(payment -> new Payment(payment.getDebtorIban(),payment.getAmount()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createPayment(Payment payment){
        paymentDao.savePayments(new Payment(payment.getDebtorIban(),payment.getAmount()));
    }
}