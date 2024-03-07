package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;

public interface ValidationService {

    public void validatePositiveLong(String valueToCheck);

    public void validatePersonDTO(PersonDTO personDTO);

}
