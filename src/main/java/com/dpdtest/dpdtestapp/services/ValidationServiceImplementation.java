package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationServiceImplementation implements ValidationService {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

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

    public void validatePersonDTO(PersonDTO personDTO) {
        if (!isValidEmail((personDTO.getEmail()))) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (personDTO.getTajNumber() != null && !isValidTajNumber(personDTO.getTajNumber())) {
            throw new IllegalArgumentException("Invalid Taj Number format");
        }
        if (personDTO.getTaxId() != null && !isValidTaxId(personDTO.getTaxId())) {
            throw new IllegalArgumentException("Invalid Tax Id format");
        }
        if (personDTO.getAddresses() == null) {
            throw new IllegalArgumentException("Address list cannot be null. Pass an empty array instead.");
        }
        if (personDTO.getPhoneNumbers() == null) {
            throw new IllegalArgumentException("Phonenumber list list cannot be null. Pass an empty array instead.");
        }
        for (String phoneNumber : personDTO.getPhoneNumbers()) {
            if (phoneNumber != null && !isValidPhoneNumber(phoneNumber)) {
                throw new IllegalArgumentException("Invalid phone number format");
            }
        }
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidTajNumber(String tajNumber) {
        if (tajNumber.length() != 9) {
            return false;
        }

        for (char c : tajNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidTaxId(String taxId) {
        if (taxId.length() != 10) {
            return false;
        }

        for (char c : taxId.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {

        phoneNumber = phoneNumber.replaceAll("\\s+", "");

        if (phoneNumber.length() < 8) {
            return false;
        }

        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c) && c != '-' && c != '+') {
                return false;
            }
        }

        return true;
    }


}
