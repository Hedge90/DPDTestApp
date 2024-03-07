package com.dpdtest.dpdtestapp.services;

import com.dpdtest.dpdtestapp.DTOs.AddressDTO;
import com.dpdtest.dpdtestapp.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;

    private final MapperService mapperService;

    @Autowired
    public AddressServiceImplementation(AddressRepository addressRepository, MapperService mapperService) {
        this.addressRepository = addressRepository;
        this.mapperService = mapperService;
    }

    @Override
    public AddressDTO saveNewAddress(AddressDTO addressDTO) {
        addressRepository.save(mapperService.convertAddressDTOToAddress(addressDTO));
        return addressDTO;
    }
}
