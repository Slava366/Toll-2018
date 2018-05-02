package controllers;

import jdev.dto.repo.StateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {StateController.class})
public class StateControllerTest {

    private String json = "{\"id\":0,\"latitude\":56,\"longitude\":84,\"autoId\":\"B666AD\",\"time\":1525172348768,\"speed\":59,\"azimuth\":89}";

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
}