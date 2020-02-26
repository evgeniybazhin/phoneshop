package com.es.core.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumber, String> {
    @Override
    public void initialize(ContactNumber contactNumber) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null && phoneNumber.matches("[0-9]+") && (phoneNumber.length() > 8) && (phoneNumber.length() < 13);
    }
}
