package com.luminor.internship;

import com.luminor.internship.controller.dto.PaymentRequest;
import com.luminor.internship.controller.dto.PaymentResponse;
import com.luminor.internship.repository.PaymentRepository;
import com.luminor.internship.repository.dao.Payment;
import com.luminor.internship.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentService service;

    @Test
    public void getPaymentTest_returnsSameListWithSameSize(){
        List<Payment> testList = List.of(
                new Payment("EE356437978869712537",BigDecimal.valueOf(15.0)),
                new Payment("LT356437978869712537",BigDecimal.valueOf(15.1)));
        when(repository.getPayments()).thenReturn(testList);

        assertEquals(2,repository.getPayments().size());
    }

    @Test
    public void createPaymentTest_returnsOkResponseEntityIfCreatedSuccessfully(){
        PaymentRequest paymentRequest = new PaymentRequest(BigDecimal.valueOf(15.0),"LT356437978869712537");
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        when(repository.save(any(Payment.class))).thenReturn(payment);

        PaymentResponse result = service.createPayment(paymentRequest);

        assertEquals(payment.getId(), result.getId());
    }
}