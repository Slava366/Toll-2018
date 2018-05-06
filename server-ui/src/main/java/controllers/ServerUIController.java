package controllers;

import jdev.dto.State;
import jdev.dto.repo.StateRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ServerUIController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerUIController.class);

    @Autowired
    StateRepository stateRepository;

    @RequestMapping("/routes")
    public String getStates(@RequestParam(value = "autoId", defaultValue = "B666AD") String autoId,
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
        ArrayList<State> states = (ArrayList<State>) stateRepository.findAll();
        model.addAttribute("states", states);
        return "routes";
    }
}
