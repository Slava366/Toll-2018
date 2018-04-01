package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StateController {

    private static Logger LOG = LoggerFactory.getLogger(StateController.class);


    /**
     * Сохраняет полученные от tracker-core координаты в log
     * @param stateInfo
     * @return
     */
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public ServerResponse getState(@RequestParam(value = "stateInfo", defaultValue = "") String stateInfo) {
        // Формируем ответ
        ServerResponse serverResponse;
        // Проверяем корректность данных
        try {
            new ObjectMapper().readValue(stateInfo, State.class);
            // Данные корректны
            serverResponse = new ServerResponse(true);
            LOG.info("{\"success\":true} FOR: " + stateInfo);
        } catch (Exception e) {
            // Данные не корректны
            serverResponse = new ServerResponse(false);
            LOG.warn("{\"success\":false} WITH: " + e.getMessage());
        }
        // Возвращаем ответ
        return serverResponse;
    }
}
