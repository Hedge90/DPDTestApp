package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Address;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.repositories.AddressRepository;
import com.dpdtest.dpdtestapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImplementation implements PersonService {

    private PersonRepository personRepository;

    private AddressRepository addressRepository;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public PersonDTO saveNewPerson(PersonDTO personDTO) {

        Person person = new Person(personDTO.getName(), personDTO.getDateOfBirth(), personDTO.getPlaceOfBirth(), personDTO.getMothersName(), personDTO.getTajNumber(), personDTO.getTaxId(), personDTO.getEmail());
        for (AddressDTO addressDTO : personDTO.getAddresses()) {
            person.addAddress(new Address(addressDTO.getZipCode(), addressDTO.getCity(), addressDTO.getStreetName(), addressDTO.getStreetNumber(), addressDTO.getFloorNumber(),addressDTO.getDoorNumber()));
        }

        Person savedPerson = personRepository.save(person);

        return personDTO;

    }
}
