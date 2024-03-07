package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;

public interface PersonService {

    public PersonDTO saveNewPerson(PersonDTO personDTO);

    public PersonDTO findPersonById(Long id);

}
