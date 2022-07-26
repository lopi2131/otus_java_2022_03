package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.hibernate.core.repository.DataTemplateHibernate;
import ru.otus.hibernate.core.repository.HibernateUtils;
import ru.otus.hibernate.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.hibernate.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.hibernate.crm.model.Address;
import ru.otus.hibernate.crm.model.Client;
import ru.otus.hibernate.crm.model.Phone;
import ru.otus.hibernate.crm.service.DbServiceClientImpl;
import ru.otus.dao.AdminDao;
import ru.otus.dao.InMemoryAdminDao;
import ru.otus.server.WebServer;
import ru.otus.server.WebServerWithFilterBasedSecurity;
import ru.otus.services.AdminAuthServiceImpl;
import ru.otus.services.AuthService;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;

public class WebServerWithFilterBasedSecurityDemoHW {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {

        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");
        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        AdminDao adminDao = new InMemoryAdminDao();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);


        AuthService authService = new AdminAuthServiceImpl(adminDao);

        WebServer webServer = new WebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceClient, gson, templateProcessor);

        webServer.start();
        webServer.join();
    }
}
