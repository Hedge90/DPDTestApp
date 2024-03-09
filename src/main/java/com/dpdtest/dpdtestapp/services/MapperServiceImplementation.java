package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Address;
import com.dpdtest.dpdtestapp.entities.Person;
import com.dpdtest.dpdtestapp.entities.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperServiceImplementation implements MapperService {

    public MapperServiceImplementation() {
    }

    @Override
    public AddressDTO convertAddressToAddressDTO(Address address) {
        return new AddressDTO(
                address.getZipCode(),
                address.getCity(),
                address.getStreetName(),
                address.getStreetNumber(),
                address.getFloorNumber(),
                address.getDoorNumber());
    }

    @Override
    public Address convertAddressDTOToAddress(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getZipCode(),
                addressDTO.getCity(),
                addressDTO.getStreetName(),
                addressDTO.getStreetNumber(),
                addressDTO.getFloorNumber(),
                addressDTO.getDoorNumber()
        );
    }

    @Override
    public Person convertPersonDTOToPerson(PersonDTO personDTO) {
        Person person = new Person(
                personDTO.getName(),
                personDTO.getDateOfBirth(),
                personDTO.getPlaceOfBirth(),
                personDTO.getMothersName(),
                personDTO.getTajNumber(),
                personDTO.getTaxId(),
                personDTO.getEmail()
        );
        for (AddressDTO addressDTO : personDTO.getAddresses()) {
            person.addAddress(convertAddressDTOToAddress(addressDTO));
        }
        for (String phoneNumber : personDTO.getPhoneNumbers()) {
            person.addPhoneNumber(new PhoneNumber(phoneNumber));
        }
        return person;
    }

    @Override
    public PersonDTO convertPersonToPersonDTO(Person person) {
        return new PersonDTO(
                person.getName(),
                person.getDateOfBirth(),
                person.getPlaceOfBirth(),
                person.getMothersName(),
                person.getTajNumber(),
                person.getTaxId(),
                person.getEmail(),
                convertAddressListToAddressDTOList(person.getAddresses()),
                convertPhoneNumberList(person.getPhoneNumbers())
        );
    }

    private List<AddressDTO> convertAddressListToAddressDTOList(List<Address> addressList) {
        List<AddressDTO> addressDTOList = new ArrayList<>();
        for (Address address : addressList) {
            addressDTOList.add(convertAddressToAddressDTO(address));
        }
        return addressDTOList;
    }

    private List<String> convertPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        List<String> convertedPhoneNumberList = new ArrayList<>();
        for (PhoneNumber phoneNumber : phoneNumberList) {
            convertedPhoneNumberList.add(phoneNumber.getPhoneNumber());
        }
        return convertedPhoneNumberList;
    }
}
