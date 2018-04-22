package controllers;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerResponseTest {

    @Test
    public void success() {
        // Создаем объект класса ServerResponse
        ServerResponse serverResponse = new ServerResponse(true);
        // Устанавливаем значения полей
        serverResponse.setSuccess(false);
        // Проверяем значения полей
        assertEquals(false, serverResponse.isSuccess());
    }

}