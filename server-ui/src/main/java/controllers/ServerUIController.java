package controllers;

import entity.Client;
import jdev.dto.State;
import jdev.dto.repo.StateRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.ClientRepository;

import java.util.ArrayList;

@Controller
public class ServerUIController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerUIController.class);

    private final StateRepository stateRepository;

    private final ClientRepository clientRepository;

    @Autowired
    public ServerUIController(StateRepository stateRepository, ClientRepository clientRepository) {
        this.stateRepository = stateRepository;
        this.clientRepository = clientRepository;
    }

    @RequestMapping("/routes")
    public String getStates(@RequestParam(value = "autoId", defaultValue = "") String autoId,
                            @RequestParam(value = "stateId", defaultValue = "0") String stateId,
                            Model model) {
        // Удаляем запись если нужно
        if(!stateId.equals("0"))
        try {
            stateRepository.delete(Integer.parseInt(stateId));
            model.addAttribute("stateId", stateId);
            // Записываем в лог
            LOG.info("Удалена метка с идентификатором id=" + stateId);
        } catch (Exception e) {
            // Записываем в лог
            LOG.error(e.getMessage());
        }
        // Считываем метки маршрута для данного пользователя
        ArrayList<State> states = stateRepository.findStatesByAutoIdIs(autoId);
        if (states.size() > 0)
        model.addAttribute("states", states);
        // Считываем всех пользователей
        ArrayList<Client> clients = (ArrayList<Client>) clientRepository.findAll();
        if(clients.size() > 0)
        model.addAttribute("clients", clients);
        // Текущий номер авто
        if(!autoId.isEmpty())
        model.addAttribute("autoId", autoId);
        return "routes";
    }

    @RequestMapping("/registerClient")
    public String addClient(@RequestParam(value = "username", defaultValue = "") String username,
                            @RequestParam(value = "password", defaultValue = "") String password,
                            @RequestParam(value = "autoId", defaultValue = "") String autoId,
                            @RequestParam(value = "clientId", defaultValue = "0") String clientId,
                            Model model) {
        // Удаляем запись если нужно
        if(!clientId.equals("0"))
            try {
                clientRepository.delete(Integer.parseInt(clientId));
                model.addAttribute("clientId", clientId);
                // Записываем в лог
                LOG.info("Удален клиент с идентификатором id=" + clientId);
            } catch (Exception e) {
                // Записываем в лог
                LOG.error(e.getMessage());
            }
        // Добавляем пользователя, если нужно
        if(!username.isEmpty()) {
            Client client = new Client();
            client.setUsername(username);
            client.setPassword(password);
            client.setAutoId(autoId);
            clientRepository.save(client);
            model.addAttribute("clientIsAdded", true);
        }
        // Считываем клиентов из базы данных
        ArrayList<Client> clients;
        clients = (ArrayList<Client>) clientRepository.findAll();
        if(clients.size() > 0)
        model.addAttribute("clients", clients);
        return "registerClient";
    }
}
