package com.luminor.internship.utils;

import com.luminor.internship.BusinessException;

import java.math.BigDecimal;

public class ValidationUtils {
    public static void validateIban(String iban) {
        if(!iban.matches("^LT\\d{18}")
                && !iban.matches("^EE\\d{18}")
                && !iban.matches("^LV\\d{2}[A-Z]{4}[A-Z0-9]{13}")) {
            throw new BusinessException("Following does not match the requirements: ", "iban");
        }
    }

    public static void validateAmount(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("Following does not match the requirements: ", "amount");
        }
    }
}