package com.luminor.internship.controller;

import com.luminor.internship.controller.dto.PaymentRequest;
import com.luminor.internship.controller.dto.PaymentResponse;
import com.luminor.internship.repository.dao.PaymentFiles;
import com.luminor.internship.service.PaymentService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payments")
    public ResponseEntity createPayment(@RequestBody PaymentRequest paymentRequest){
        try {
            return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/payments")
    public List<PaymentResponse> getPayments(){
        return paymentService.getPayments();
    }

    @PostMapping("/payment-files")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) throws Exception {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<PaymentFiles> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(PaymentFiles.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<PaymentFiles> paymentFilesList = csvToBean.parse();
            paymentService.createPaymentsFromFile(paymentFilesList);
        }
    }
}