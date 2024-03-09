package com.dpdtest.dpdtestapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.repositories.PersonRepository;
import com.dpdtest.dpdtestapp.services.MapperService;
import com.dpdtest.dpdtestapp.services.PersonServiceImplementation;
import com.dpdtest.dpdtestapp.services.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dpdtest.dpdtestapp.services.PersonService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PersonServiceTests {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MapperService mapperService;

    @Mock
    private ValidationService validationService;

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.personService = new PersonServiceImplementation(personRepository, mapperService, validationService);
    }

    @Test
    public void saveNewPerson_WithCorrectPersonDTO_ReturnsCorrectDTO() {
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                null,
                null
        );

        doNothing().when(validationService).validatePersonDTO(personDTO);

        when(mapperService.convertPersonDTOToPerson(personDTO)).thenReturn(new Person(
                personDTO.getName(),
                personDTO.getDateOfBirth(),
                personDTO.getPlaceOfBirth(),
                personDTO.getMothersName(),
                personDTO.getTajNumber(),
                personDTO.getTaxId(),
                personDTO.getEmail()
                ));

        PersonDTO result = personService.saveNewPerson(personDTO);

        assertEquals(personDTO, result);
    }

    @Test
    public void updatePerson_WithCorrectPersonDTOAndExistingId_ReturnsCorrectDTO () {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                null,
                null
        );

        doNothing().when(validationService).validatePersonDTO(personDTO);

        Person existingPerson = new Person();
        when(personRepository.findById(id)).thenReturn(Optional.of(existingPerson));

        Person updatedPerson = new Person();
        when(mapperService.convertPersonDTOToPerson(personDTO)).thenReturn(updatedPerson);

        PersonDTO result = personService.updatePerson(personDTO, id);

        assertEquals(personDTO, result);
    }

    @Test
    public void updatePerson_WithIdNotFoundInDB_ThrowsEntityNotFoundException() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                null,
                null
        );

        doNothing().when(validationService).validatePersonDTO(personDTO);

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> personService.updatePerson(personDTO, id));
    }

    @Test
    public void getListOfPersons_WithValidList_ReturnsCorrectDTOList() {
        List<Person> mockPersonList = new ArrayList<>();
        Person person1 = new Person(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com"
        );
        person1.setId(1L);
        Person person2 = new Person(
                "Lisa Jackson",
                LocalDate.of(1985, 10, 12),
                "London",
                "Eva Smith",
                "987654321",
                "9876543210",
                "lisajackson@testmail.com"
        );
        person2.setId(2L);
        mockPersonList.add(person1);
        mockPersonList.add(person2);
        when(personRepository.getListFromId(anyLong(), any(Pageable.class))).thenReturn(mockPersonList);

        PersonDTO personDTO1 = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                null,
                null
        );
        PersonDTO personDTO2 = new PersonDTO(
                "Lisa Jackson",
                LocalDate.of(1985, 10, 12),
                "London",
                "Eva Smith",
                "987654321",
                "9876543210",
                "lisajackson@testmail.com",
                null,
                null
        );

        when(mapperService.convertPersonToPersonDTO(person1)).thenReturn(personDTO1);
        when(mapperService.convertPersonToPersonDTO(person2)).thenReturn(personDTO2);

        List<PersonDTO> result = personService.getListOfPersons(10L, 1L);

        assertEquals(2, result.size());
        assertEquals("John Smith", result.get(0).getName());
        assertEquals("Lisa Jackson", result.get(1).getName());
    }

    @Test
    public void deleteDataOfPerson_WithExistingIdAndGDPROnlySetToFalse_ReturnsEmptyDTO() {
        Long id = 1L;
        Person person = new Person(
                "Lisa Jackson",
                LocalDate.of(1985, 10, 12),
                "London",
                "Eva Smith",
                "987654321",
                "9876543210",
                "lisajackson@testmail.com"
        );

        person.setId(id);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        PersonDTO result = personService.deleteDataOfPerson(id, false);

        assertNull(result.getName());
        assertNull(result.getEmail());
        assertNull(result.getTajNumber());
        assertTrue(result.getAddresses().isEmpty());
    }

    @Test
    public void deleteDataOfPerson_WithExistingIdAndGDPROnlySetToTrue_ReturnsDTOWithClearedFields() {
        Long id = 1L;
        Person person = new Person(
                "Lisa Jackson",
                LocalDate.of(1985, 10, 12),
                "London",
                "Eva Smith",
                "987654321",
                "9876543210",
                "lisajackson@testmail.com"
        );

        person.setId(id);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> {
            Person savedPerson = invocation.getArgument(0);
            return new Person(
                    savedPerson.getName(),
                    savedPerson.getDateOfBirth(),
                    savedPerson.getPlaceOfBirth(),
                    savedPerson.getMothersName(),
                    savedPerson.getTajNumber(),
                    savedPerson.getTaxId(),
                    savedPerson.getEmail()
            );
        });
        when(mapperService.convertPersonToPersonDTO(any(Person.class))).thenAnswer(invocation -> {
            Person personToConvert = invocation.getArgument(0);
            return new PersonDTO(
                    personToConvert.getName(),
                    personToConvert.getDateOfBirth(),
                    personToConvert.getPlaceOfBirth(),
                    personToConvert.getMothersName(),
                    personToConvert.getTajNumber(),
                    personToConvert.getTaxId(),
                    personToConvert.getEmail(),
                    null,
                    null
            );
        });

        PersonDTO result = personService.deleteDataOfPerson(id, true);

        assertEquals("", result.getName());
        assertNull(result.getDateOfBirth());
    }
}
