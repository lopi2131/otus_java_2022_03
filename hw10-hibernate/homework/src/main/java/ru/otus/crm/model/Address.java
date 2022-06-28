package ru.otus.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    public Address() {
    }

    public Address(Long id, String street) {
        this.id = id;
        this.street = street;
    }

    @Override
    protected Address clone() {
        return new Address(id, street);
    }
}