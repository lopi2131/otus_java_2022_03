package ru.otus.hibernate.demo;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hibernate.core.repository.DataTemplateHibernate;
import ru.otus.hibernate.core.repository.HibernateUtils;
import ru.otus.hibernate.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.hibernate.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.hibernate.crm.model.Address;
import ru.otus.hibernate.crm.model.Client;
import ru.otus.hibernate.crm.model.Phone;
import ru.otus.hibernate.crm.service.DbServiceClientImpl;

import java.util.List;

public class DbServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
///
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);
        dbServiceClient.saveClient(new Client("Иванов Василий Петрович", new Address("Садовая 37"),
                List.of(new Phone("8 (999) 111-11-11"), new Phone("8 (999) 222-22-22"))));

        var clientSecond = dbServiceClient.saveClient(new Client("Пирожков Владимир Влидмирович", new Address("Ленина 10"),
                List.of(new Phone("8 (999) 333-33-33"), new Phone("8 (999) 444-44-44"))));
        var clientSecondSelected = dbServiceClient.getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);
///
       /* dbServiceClient.saveClient(new Client(clientSecondSelected.getId(), "dbServiceSecondUpdated"));
        var clientUpdated = dbServiceClient.getClient(clientSecondSelected.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecondSelected.getId()));
        log.info("clientUpdated:{}", clientUpdated);

        log.info("All clients");
        dbServiceClient.findAll().forEach(client -> log.info("client:{}", client));*/
    }
}
