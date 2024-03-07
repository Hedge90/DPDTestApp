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

    private Long tajNumber;

    private Long taxId;

    private String email;

    private List<AddressDTO> addresses = new ArrayList<>();

    private List<String> phoneNumbers = new ArrayList<>();

    public PersonDTO() {}

    public PersonDTO(String name, LocalDate dateOfBirth, String placeOfBirth, String mothersName, Long tajNumber, Long taxId, String email, List<AddressDTO> addresses, List<String> phoneNumbers) {
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

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public Long getTajNumber() {
        return tajNumber;
    }

    public void setTajNumber(Long TAJnumber) {
        this.tajNumber = TAJnumber;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
