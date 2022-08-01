package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.jdbc.crm.model.Address;
import ru.otus.jdbc.crm.model.Client;
import ru.otus.jdbc.crm.model.Phone;
import ru.otus.jdbc.crm.service.DbServiceClient;
import ru.otus.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClientController {
    private final DbServiceClient dbServiceClient;

    public ClientController(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @GetMapping({"/", "/clients"})
    public String clientsListView(Model model) {
        List<ClientService> clients = dbServiceClient.findAll().stream().map(ClientService::new).toList();
        model.addAttribute("clients", clients);
        model.addAttribute("clientService", new ClientService());
        return "clients";
    }

    @PostMapping("/client/save")
    public RedirectView clientSave(@ModelAttribute ClientService clientService) {
        var name = clientService.getName();
        var address = new Address(clientService.getAddress(), null);
        var phones = Stream.of(clientService.getPhones().split(";")).map(number -> new Phone(number, null)).collect(Collectors.toSet());
        dbServiceClient.saveClient(new Client(name, address, phones));
        return new RedirectView("/", true);
    }

}
