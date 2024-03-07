package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;

    private final MapperService mapperService;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository, MapperService mapperService) {
        this.personRepository = personRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Transactional
    public PersonDTO saveNewPerson(PersonDTO personDTO) {
        Person person = mapperService.convertPersonDTOToPerson(personDTO);
        personRepository.save(person);
        return personDTO;
    }

    @Override
    public PersonDTO findPersonById(Long id) {
        return mapperService.convertPersonToPersonDTO(personRepository.findById(id).orElse(null));
    }
}
