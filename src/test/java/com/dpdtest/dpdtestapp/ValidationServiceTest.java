package com.dpdtest.dpdtestapp;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.services.PersonServiceImplementation;
import com.dpdtest.dpdtestapp.services.ValidationService;
import com.dpdtest.dpdtestapp.services.ValidationServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    public void setUp() {
        this.validationService = new ValidationServiceImplementation();
    }

    @Test
    public void validatePositiveLong_WithNonNumericValue_ThrowsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> validationService.validatePositiveLong("abc"));
    }

    @Test
    public void validatePositiveLong_WithPositiveValue_DoesNotThrowException() {
        validationService.validatePositiveLong("10");
    }

    @Test
    public void validatePersonDTO_WithCorrectDTO_DoesNotThrowException() {
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                new ArrayList<>(),
                new ArrayList<>()
        );

        validationService.validatePersonDTO(personDTO);
    }

    @Test
    public void validatePersonDTO_WithInvalidEmail_ThrowsIllegalArgumentException() {
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmithtestmail.com",
                new ArrayList<>(),
                new ArrayList<>()
        );

        assertThrows(IllegalArgumentException.class, () -> validationService.validatePersonDTO(personDTO));

    }

    @Test
    public void validatePersonDTO_WithNullAddressList_ThrowsIllegalArgumentException() {
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmithtestmail.com",
                null,
                new ArrayList<>()
        );

        assertThrows(IllegalArgumentException.class, () -> validationService.validatePersonDTO(personDTO));

    }

    @Test
    public void validatePersonDTO_WithIllegalTajNumber_ThrowsIllegalArgumentException() {
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "1234567890",
                "0123456789",
                "johnsmithtestmail.com",
                null,
                new ArrayList<>()
        );

        assertThrows(IllegalArgumentException.class, () -> validationService.validatePersonDTO(personDTO));

    }

}
