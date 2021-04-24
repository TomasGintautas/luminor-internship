package com.luminor.internship.controller;


import com.luminor.internship.repository.dao.Payment;
import com.luminor.internship.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment")
    public void createPayment(@RequestBody Payment payment){
        paymentService.createPayment(payment);
    }

    @PostMapping(value = "/payments")
    @GetMapping(value = "/payments")
    public List<Payment> getPayments(){
        return paymentService.getPayments();
    }
}