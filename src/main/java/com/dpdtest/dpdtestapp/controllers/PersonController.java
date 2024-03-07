package com.dpdtest.dpdtestapp.controllers;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.services.PersonService;
import com.dpdtest.dpdtestapp.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {

    private final PersonService personService;

    private final ValidationService validationService;

    @Autowired
    public PersonController(PersonService personService, ValidationService validationService) {
        this.personService = personService;
        this.validationService = validationService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<PersonDTO>> getListOfPersons(@RequestParam(defaultValue = "10") String index,
                                                            @RequestParam(defaultValue = "1") String length) {
        validationService.validatePositiveLong(index);
        validationService.validatePositiveLong(length);
        List<PersonDTO> personDTOList = personService.getListOfPersons(Long.parseLong(length), Long.parseLong(index));
        return new ResponseEntity<>(personDTOList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PersonDTO> getPersonById(@RequestParam String id) {
        validationService.validatePositiveLong(id);
        PersonDTO personDTO = personService.findPersonById(Long.parseLong(id));
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        personService.saveNewPerson(personDTO);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePersonById(@RequestBody PersonDTO personDTO,
                                                      @RequestParam String id) {
        validationService.validatePositiveLong(id);
        personService.updatePerson(personDTO, Long.parseLong(id));
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDataOfPerson(@RequestParam String id,
                                                @RequestParam(defaultValue = "False") String GDPROnly) {
        validationService.validatePositiveLong(id);
        boolean GPDROnlyAsBoolean = Boolean.parseBoolean(GDPROnly);
        PersonDTO personDTO = personService.deleteDataOfPerson(Long.parseLong(id), GPDROnlyAsBoolean);
        if (GPDROnlyAsBoolean) {
            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The data of person id " + id.toString() + " has been deleted", HttpStatus.OK);
        }
    }
}
