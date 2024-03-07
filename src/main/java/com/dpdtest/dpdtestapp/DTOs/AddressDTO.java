package com.dpdtest.dpdtestapp.DTOs;

public class AddressDTO {

    private Long zipCode;

    private String city;

    private String streetName;

    private String streetNumber;

    private String floorNumber;

    private String doorNumber;

    public AddressDTO() {};

    public AddressDTO(Long zipCode, String city, String streetName, String streetNumber, String floorNumber, String doorNumber) {
        this.zipCode = zipCode;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.floorNumber = floorNumber;
        this.doorNumber = doorNumber;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public String getDoorNumber() {
        return doorNumber;
    }
}
