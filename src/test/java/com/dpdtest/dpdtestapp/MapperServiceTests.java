package com.dpdtest.dpdtestapp;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Address;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.entities.PhoneNumber;
import com.dpdtest.dpdtestapp.services.MapperServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperServiceTests {

    private MapperServiceImplementation mapperServiceImplementation;

    @BeforeEach
    public void setUp() { this.mapperServiceImplementation = new MapperServiceImplementation(); }

    @Test
    public void convertAddressToAddressDTO_WithCorrectAddress_ReturnsCorrectAddressDTO() {
        Address address = new Address(
                7100L,
                "London",
                "Main road",
                "5",
                "3",
                "1"
        );

        AddressDTO result = mapperServiceImplementation.convertAddressToAddressDTO(address);

        assertEquals(7100L, result.getZipCode());
        assertEquals("London", result.getCity());
        assertEquals("Main road", result.getStreetName());
        assertEquals("5", result.getStreetNumber());
        assertEquals("3", result.getFloorNumber());
        assertEquals("1", result.getDoorNumber());
    }

    @Test
    public void convertAddressDTOToAddress_WithCorrectAddressDTO_ReturnsCorrectAddress() {
        AddressDTO addressDTO = new AddressDTO(
                7100L,
                "London",
                "Main road",
                "5",
                "3",
                "1"
        );

        Address result = mapperServiceImplementation.convertAddressDTOToAddress(addressDTO);

        assertEquals(7100L, result.getZipCode());
        assertEquals("London", result.getCity());
        assertEquals("Main road", result.getStreetName());
        assertEquals("5", result.getStreetNumber());
        assertEquals("3", result.getFloorNumber());
        assertEquals("1", result.getDoorNumber());
    }

    @Test
    public void convertPersonDTOToPerson_WithCorrectPersonDTO_ReturnsCorrectPerson() {
        List<AddressDTO> addressList = new ArrayList<>();
        AddressDTO addressDTO = new AddressDTO(
                7100L,
                "London",
                "Main road",
                "5",
                "3",
                "1"
        );
        addressList.add(addressDTO);

        List<String> phoneNumberList = new ArrayList<>();
        phoneNumberList.add("123456789");

        PersonDTO personDTO = new PersonDTO(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com",
                addressList,
                phoneNumberList
        );

        Person result = mapperServiceImplementation.convertPersonDTOToPerson(personDTO);

        assertEquals("John Smith", result.getName());
        assertEquals(LocalDate.of(1985, 10, 12), result.getDateOfBirth());
        assertEquals("Washington", result.getPlaceOfBirth());
        assertEquals("Jane Smith", result.getMothersName());
        assertEquals("123456789", result.getTajNumber());
        assertEquals("0123456789", result.getTaxId());
        assertEquals("johnsmith@testmail.com", result.getEmail());
        assertEquals("London", result.getAddresses().get(0).getCity());
        assertEquals("123456789", result.getPhoneNumbers().get(0).getPhoneNumber());
    }

    @Test
    public void convertPersonToPersonDTO_WithCorrectPerson_ReturnsCorrectPersonDTO() {
        Address address = new Address(7100L,
                "London",
                "Main road",
                "5",
                "3",
                "1"
        );

        PhoneNumber phoneNumber = new PhoneNumber("123456789");

        Person person = new Person(
                "John Smith",
                LocalDate.of(1985, 10, 12),
                "Washington",
                "Jane Smith",
                "123456789",
                "0123456789",
                "johnsmith@testmail.com"
        );

        person.addAddress(address);
        person.addPhoneNumber(phoneNumber);

        PersonDTO result = mapperServiceImplementation.convertPersonToPersonDTO(person);

        assertEquals("John Smith", result.getName());
        assertEquals(LocalDate.of(1985, 10, 12), result.getDateOfBirth());
        assertEquals("Washington", result.getPlaceOfBirth());
        assertEquals("Jane Smith", result.getMothersName());
        assertEquals("123456789", result.getTajNumber());
        assertEquals("0123456789", result.getTaxId());
        assertEquals("johnsmith@testmail.com", result.getEmail());
        assertEquals("London", result.getAddresses().get(0).getCity());
        assertEquals("123456789", result.getPhoneNumbers().get(0));
    }
}
