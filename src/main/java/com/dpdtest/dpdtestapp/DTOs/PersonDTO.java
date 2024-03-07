package com.dpdtest.dpdtestapp.DTOs;

import com.dpdtest.dpdtestapp.entities.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO {

    private String name;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String mothersName;

    private String tajNumber;

    private String taxId;

    private String email;

    private List<AddressDTO> addresses = new ArrayList<>();

    private List<String> phoneNumbers = new ArrayList<>();

    public PersonDTO() {}

    public PersonDTO(String name, LocalDate dateOfBirth, String placeOfBirth, String mothersName, String tajNumber, String taxId, String email, List<AddressDTO> addresses, List<String> phoneNumbers) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.mothersName = mothersName;
        this.tajNumber = tajNumber;
        this.taxId = taxId;
        this.email = email;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getMothersName() {
        return mothersName;
    }

    public String getTajNumber() {
        return tajNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getEmail() {
        return email;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}
