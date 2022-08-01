package ru.otus.services;

import ru.otus.jdbc.crm.model.Client;
import ru.otus.jdbc.crm.model.Phone;

import java.util.stream.Collectors;

public class ClientService {
    private String id;
    private String name;
    private String address;
    private String phones;

    public ClientService() {
    }

    public ClientService(Client client) {
        id = client.getId().toString();
        name = client.getName();
        address = client.getAddress().getStreet();
        phones = client.getPhones().stream().map(Phone::getNumber).collect(Collectors.joining("; "));
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhones() {
        return phones;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
