package com.dpdtest.dpdtestapp.controllers;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<?> getPerson(@RequestParam Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> postPerson(@RequestBody PersonDTO personDTO) {
        personService.saveNewPerson(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

}
