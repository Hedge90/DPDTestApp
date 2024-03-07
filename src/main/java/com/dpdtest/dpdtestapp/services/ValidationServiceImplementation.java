package com.dpdtest.dpdtestapp.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class ValidationServiceImplementation implements ValidationService {

    public ValidationServiceImplementation() {}

    public void validatePositiveLong(String valueToCheck) {
        try {
            if (valueToCheck == null || Long.parseLong(valueToCheck) <= 0) {
                throw new InvalidParameterException("Querystring parameter was not a positive number");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Querystring parameter was not a positive number");
        }
    }
}
