package com.dpdtest.dpdtestapp.controllers;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> getPerson(@RequestParam Long id) {
        PersonDTO personDTO = personService.findPersonById(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        personService.saveNewPerson(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updateAddressesOfPerson(@RequestBody List<AddressDTO> addressDTOList) {
        return null;
    }

}
