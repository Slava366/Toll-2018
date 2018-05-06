package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.State;
import jdev.dto.repo.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@EnableJpaRepositories("jdev.dto")
@EntityScan(basePackageClasses = jdev.dto.State.class)
public class StateController {

    private static Logger LOG = LoggerFactory.getLogger(StateController.class);

    private final StateRepository stateRepository;

    @Autowired
    public StateController(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

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
            State stateInfoObject = new ObjectMapper().readValue(stateInfo, State.class);
            // Данные корректны
            serverResponse = new ServerResponse(true);
            // Сохраняем в базу данных
            stateRepository.save(stateInfoObject);
            // Записываем в лог
            LOG.info("{\"success\":true} FOR: " + stateInfo);
        } catch (Exception e) {
            // Данные не корректны
            serverResponse = new ServerResponse(false);
            LOG.warn("{\"success\":false} WITH: " + e.getMessage());
        }
        // Возвращаем ответ
        return serverResponse;
    }


    /**
     * Возвращает максимально возможное число самых поздних по времени отметок
     */
    @RequestMapping(value = "/lastStates", method = RequestMethod.POST)
    public String queryState(@RequestParam(value = "stateQuery", defaultValue = "") String stateQuery) throws JsonProcessingException {
        // Формируем ответ
        ArrayList<State> response = new ArrayList<>();
        // Проверяем корректность запроса
        try {
            ServerQuery query = new ObjectMapper().readValue(stateQuery, ServerQuery.class);
            // Правильный запрос
            response = stateRepository.findStatesByAutoIdIs(query.getAutoId());  // Считываем метки
            // Удаляем лишнее
            int size = response.size();
            if(query.getMaxAmount() < size)
            for (int i = 0; i < size - query.getMaxAmount(); i++) {
                response.remove(0);
            }
            System.out.println(response.size());
            // Записываем лог
            LOG.info("DONE FOR: " + stateQuery);
        } catch (IOException e) {
            // Неверный запрос
            LOG.error(e.getMessage() + " FOR: " + stateQuery);
        }
        // Возвращаем результат запроса
        return new ObjectMapper().writeValueAsString(response);
    }
}
