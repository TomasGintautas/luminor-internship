package com.luminor.internship.utils;

import com.luminor.internship.BusinessException;

public class ValidationUtils {
    public static void validateIban(String iban) {
        if(iban.matches("^LT|^LV|^EE")) {
            throw new BusinessException("Following does not match the requirements: ", "iban");
        }
    }
}
