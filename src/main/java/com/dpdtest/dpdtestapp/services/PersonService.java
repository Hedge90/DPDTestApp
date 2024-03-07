package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;

import java.util.List;

public interface PersonService {

    public PersonDTO saveNewPerson(PersonDTO personDTO);

    public PersonDTO findPersonById(Long id);

    public PersonDTO updatePerson(PersonDTO personDTO, Long id);

    public List<PersonDTO> getListOfPersons(Long length, Long id);

    public PersonDTO deleteDataOfPerson(Long id, boolean GDPROnly);

}
