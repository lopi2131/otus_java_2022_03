package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.jdbc.core.repository.ClientRepository;
import ru.otus.jdbc.crm.model.Address;
import ru.otus.jdbc.crm.model.Client;
import ru.otus.jdbc.crm.model.Phone;
import ru.otus.jdbc.crm.service.DbServiceClient;

import java.util.Set;

@Component("actionDemo")
public class ActionDemo {

    private final DbServiceClient dbServiceClient;

    public ActionDemo(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    void action() {
        dbServiceClient.saveClient(new Client("Галина", new Address("Кирова ул., д. 15 кв.185", null),
                Set.of(new Phone("8(999) 999-99-99", null), new Phone("8(888) 333-33-33", null))));
        dbServiceClient.saveClient(new Client("Василий", new Address("Гагарина ул., д. 14 кв.193", null),
                Set.of(new Phone("8(999) 888-88-88", null), new Phone("8(888) 333-33-33", null))));
        dbServiceClient.saveClient(new Client("Анна", new Address("Дачная ул., д. 24 кв.204", null),
                Set.of(new Phone("8(999) 777-77-77", null), new Phone("8(888) 321-32-21", null))));
        dbServiceClient.saveClient(new Client("Афанасий", new Address("Кирова ул., д. 25 кв.85", null),
                Set.of(new Phone("8(999) 666-66-66", null), new Phone("8(888) 132-12-21", null))));
        dbServiceClient.saveClient(new Client("Вячеслав", new Address("Лесная ул., д. 5 кв.189", null),
                Set.of(new Phone("8(999) 555-55-55", null), new Phone("8(888) 111-11-11", null))));
        dbServiceClient.saveClient(new Client("Дмитрий", new Address("Коммунистическая ул., д. 25 кв.195", null),
                Set.of(new Phone("8(999) 444-44-44", null), new Phone("8(888) 222-22-22", null))));
    }
}
