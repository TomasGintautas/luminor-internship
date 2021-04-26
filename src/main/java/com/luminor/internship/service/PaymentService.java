package com.luminor.internship.service;

import com.luminor.internship.controller.dto.PaymentRequest;
import com.luminor.internship.controller.dto.PaymentResponse;
import com.luminor.internship.repository.PaymentRepository;
import com.luminor.internship.repository.dao.Payment;
import com.luminor.internship.repository.dao.PaymentFiles;
import com.luminor.internship.utils.ValidationUtils;
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

    public List<PaymentResponse> getPayments(){
        return paymentDao.getPayments().stream()
                .map(payment -> new PaymentResponse(payment.getId(),payment.getTimestamp(),payment.getDebtorIban(),payment.getAmount()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PaymentResponse createPayment(PaymentRequest paymentRequest){
        ValidationUtils.validateIban(paymentRequest.getDebtorIban());
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(paymentDao.save(new Payment(paymentRequest.getDebtorIban(),paymentRequest.getAmount())).getId());
        return paymentResponse;
    }

    @Transactional
    public void createPaymentsFromFile(List<PaymentFiles> paymentFilesList){
        for(PaymentFiles paymentFiles: paymentFilesList){
            paymentDao.save(new Payment(paymentFiles.getDebtorIban(),paymentFiles.getAmount()));
        }
    }
}