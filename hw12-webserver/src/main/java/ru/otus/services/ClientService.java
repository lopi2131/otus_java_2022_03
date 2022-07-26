package ru.otus.services;

import ru.otus.hibernate.crm.model.Client;
import ru.otus.hibernate.crm.model.Phone;

import java.util.stream.Collectors;


public class ClientService {
    private final String id;
    private final String name;
    private final String address;
    private final String phones;


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
}
