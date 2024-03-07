package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonRepository personRepository;

    private final MapperService mapperService;

    private final ValidationService validationService;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository, MapperService mapperService, ValidationService validationService) {
        this.personRepository = personRepository;
        this.mapperService = mapperService;
        this.validationService = validationService;
    }

    @Override
    public PersonDTO saveNewPerson(PersonDTO personDTO) {
        validationService.validatePersonDTO(personDTO);
        Person person = mapperService.convertPersonDTOToPerson(personDTO);
        personRepository.save(person);
        return personDTO;
    }

    @Override
    public PersonDTO findPersonById(Long id) {
        return mapperService.convertPersonToPersonDTO(personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id)));
    }

    @Override
    public PersonDTO updatePerson(PersonDTO personDTO, Long id) {
        validationService.validatePersonDTO(personDTO);
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        Person updatedPerson = mapperService.convertPersonDTOToPerson(personDTO);
        updatedPerson.setId(person.getId());
        personRepository.save(updatedPerson);
        return personDTO;
    }

    @Override
    public List<PersonDTO> getListOfPersons(Long length, Long id) {
        Pageable pageable = PageRequest.of(0, length.intValue());
        List<Person> personList = personRepository.getListFromId(id, pageable);
        if (personList.isEmpty()) {
            throw new EntityNotFoundException("There are no persons with the ids under the specified range");
        }
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (Person person : personList) {
            personDTOList.add(mapperService.convertPersonToPersonDTO(person));
        }
        return personDTOList;
    }

    @Override
    public PersonDTO deleteDataOfPerson(Long id, boolean GDPROnly) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
        if (!GDPROnly) {
            personRepository.deleteById(id);
            return new PersonDTO();
        } else {
            System.out.println("We are here");
            person.setName("");
            person.setDateOfBirth(null);
            person.setPlaceOfBirth("");
            person.setEmail("");
            person.setMothersName("");
            person.setTajNumber(null);
            person.setTaxId(null);
            person.removeAddresses();
            person.removePhoneNumbers();
            personRepository.save(person);
        }
        return mapperService.convertPersonToPersonDTO(person);
    }
}
