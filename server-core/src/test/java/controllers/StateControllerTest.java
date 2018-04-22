package controllers;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateControllerTest {

    private String json = "{\"latitude\":56.0,\"longitude\":74.0,\"autoId\":\"o567gfd\",\"time\":1521964156074,\"speed\":95.3,\"azimuth\":89.1}";

    @Test
    public void getState() {
        // Создаем объект
        StateController controller = new StateController();
        // Отправляем неверную строку
        ServerResponse response = controller.getState("state");
        // Проверяем правильность результата
        assertEquals(false, response.isSuccess());
        // Отправляем корректную строку json состояния
        response = controller.getState(json);
        // Проверяем правильность результата
        assertEquals(true, response.isSuccess());
    }
}