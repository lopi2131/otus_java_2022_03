package ru.otus.jdbc.core.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.jdbc.crm.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
