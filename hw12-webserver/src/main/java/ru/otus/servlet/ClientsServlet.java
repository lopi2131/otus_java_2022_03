package ru.otus.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.hibernate.crm.model.Address;
import ru.otus.hibernate.crm.model.Client;
import ru.otus.hibernate.crm.model.Phone;
import ru.otus.hibernate.crm.service.DBServiceClient;
import ru.otus.services.ClientService;
import ru.otus.services.TemplateProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClientsServlet extends HttpServlet {

    private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
    private static final String TEMPLATE_ATTR_CLIENTS = "clients";
    private static final String TEMPLATE_CLIENT_NAME = "clientNameTextBox";
    private static final String TEMPLATE_CLIENT_ADDRESS = "clientAddressTextBox";
    private static final String TEMPLATE_CLIENT_PHONE = "clientPhoneTextBox";

    private final DBServiceClient serviceClient;
    private final TemplateProcessor templateProcessor;

    public ClientsServlet(TemplateProcessor templateProcessor, DBServiceClient dbServiceClient) {
        this.templateProcessor = templateProcessor;
        this.serviceClient = dbServiceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        List<ClientService> clients = serviceClient.findAll().stream().map(ClientService::new).collect(Collectors.toList());

        paramsMap.put(TEMPLATE_ATTR_CLIENTS, clients);

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var name = req.getParameter(TEMPLATE_CLIENT_NAME);
        var address = new Address(req.getParameter(TEMPLATE_CLIENT_ADDRESS));
        var phones = Stream.of(req.getParameter(TEMPLATE_CLIENT_PHONE).split("; ")).map(Phone::new).collect(Collectors.toList());
        serviceClient.saveClient(new Client(name, address, phones));
        resp.sendRedirect("clients");
    }
}
