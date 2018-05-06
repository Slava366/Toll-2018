package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.State;
import jdev.dto.repo.StateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {StateController.class})
public class StateControllerTest {

    private String json = "{\"id\":0,\"latitude\":56,\"longitude\":84,\"autoId\":\"B666AD\",\"time\":1525172348768,\"speed\":59,\"azimuth\":89}";
    private String query = "{\"autoId\":\"B666AD\",\"maxAmount\":4}";

    @Autowired
    StateRepository stateRepository;


    @Test
    public void getState() {
        // Создаем объект
        StateController controller = new StateController(stateRepository);
        // Отправляем неверный запрос и проверяем ответ
        ServerResponse response = controller.getState("State");
        assertFalse(response.isSuccess());
        // Отправляем верный запрос и проверяем ответ
        response = controller.getState(json);
        assertTrue(response.isSuccess());
    }


    @Test
    public void queryState() throws IOException {
        // Наполняем таблицу
        for (int i = 0; i < 20; i++) {
            stateRepository.save(new ObjectMapper().readValue(json, State.class));
        }
        // Создаем объект
        StateController controller = new StateController(stateRepository);
        // Отправляем неверный запрос и проверяем ответ
        String response = controller.queryState("State");
        assertEquals("[]", response);
        // Отправляем верный запрос и проверяем ответ
        response = controller.queryState(query);
        assertTrue(response.length() > 2);
    }
}