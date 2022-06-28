package ru.otus.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Phone() {
    }

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    protected Phone clone() {
        return new Phone(id, number);
    }
}