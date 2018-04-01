package controllers;

import jdev.dto.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;

@RestController
public class StateController {

    private static Logger LOG = LoggerFactory.getLogger(StateController.class);


    /**
     * Сохраняет полученные от tracker-core координаты в log
     * @param stateInfo
     * @return
     */
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public ServerResponse getState(@RequestParam(value = "stateInfo", defaultValue = "{}") String stateInfo) {
        // Проверяем корректность данных
        boolean isStateInfo = true;
        for (Field field : State.class.getDeclaredFields()) {
            if(!stateInfo.contains("\"" + field.getName() + "\"")) isStateInfo = false;
        }
        // Формируем ответ
        ServerResponse serverResponse;
        if(isStateInfo) {
            serverResponse = new ServerResponse(true);
            LOG.info(stateInfo);
        } else {
            serverResponse = new ServerResponse(false);
            LOG.warn(stateInfo);
        }
        // Возвращаем ответ
        return serverResponse;
    }
}
