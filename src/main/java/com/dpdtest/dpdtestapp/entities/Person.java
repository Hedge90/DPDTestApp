package com.dpdtest.dpdtestapp.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String placeOfBirth;

    private String mothersName;

    private Long tajNumber;

    private Long taxId;

    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Person() {}

    public Person(String name, LocalDate dateOfBirth, String placeOfBirth, String mothersName, Long tajNumber, Long taxId, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.mothersName = mothersName;
        this.tajNumber = tajNumber;
        this.taxId = taxId;
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        address.setPerson(this);
        addresses.add(address);
    }

}
