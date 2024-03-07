package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.DTOs.PersonDTO;
import com.dpdtest.dpdtestapp.entities.Address;
import com.dpdtest.dpdtestapp.entities.Person;

public interface MapperService {

    public AddressDTO convertAddressToAddressDTO(Address address);

    public Address convertAddressDTOToAddress(AddressDTO addressDTO);

    public Person convertPersonDTOToPerson(PersonDTO personDTO);

    public PersonDTO convertPersonToPersonDTO(Person person);

}
