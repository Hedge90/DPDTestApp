package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.entities.Address;
import com.dpdtest.dpdtestapp.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImplementation implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDTO saveNewAddress(AddressDTO addressDTO) {
        addressRepository.save(new Address(addressDTO.getZipCode(), addressDTO.getCity(), addressDTO.getStreetName(), addressDTO.getDoorNumber(), addressDTO.getFloorNumber(), addressDTO.getDoorNumber()));
        return addressDTO;
    }

}
