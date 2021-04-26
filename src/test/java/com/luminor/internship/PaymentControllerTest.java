package com.luminor.internship;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.luminor.internship.controller.PaymentController;
import com.luminor.internship.controller.dto.PaymentRequest;
import com.luminor.internship.controller.dto.PaymentResponse;
import com.luminor.internship.repository.PaymentRepository;
import com.luminor.internship.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PaymentController.class)

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController controller;

    @MockBean
    private PaymentService service;

    @Autowired
    private MockMvc mvc;

    @Mock
    private PaymentRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void createPaymentTest_returnsOkResponseEntityIfCreatedSuccessfully() throws Exception{
        PaymentRequest paymentRequest = new PaymentRequest(BigDecimal.valueOf(15.0),"LT356437978869712537");
        PaymentResponse paymentResponse = new PaymentResponse();
        when(service.createPayment(any(PaymentRequest.class))).thenReturn(paymentResponse);

        mvc.perform(post("/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(paymentRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(convertObjectToJsonString(paymentResponse)));
    }

    @Test
    public void createPaymentTest_returnsOkResponseEntityIfCreatedUnsuccessfully() throws Exception{
        PaymentRequest paymentRequest = new PaymentRequest(BigDecimal.valueOf(15.0),"LT356437978869712537");
        when(service.createPayment(any(PaymentRequest.class))).thenThrow(new BusinessException("Error", "Err"));

        mvc.perform(post("/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(paymentRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Configuration
    static class TestConfig {
    }

    private String convertObjectToJsonString(Object object) throws JsonProcessingException {
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(object);
    }
}