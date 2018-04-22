package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateTest {

    private String json = "{\"latitude\":56.0,\"longitude\":74.0,\"autoId\":\"o567gfd\",\"time\":1521964156074,\"speed\":95.3,\"azimuth\":89.1}";
    private String autoId = "o567gfd";

    /**
     * Из объетка в строку
     */
    @Test
    public void encodeDTO() throws Exception {
        State state = new State();      // Создаем объект
        // Устанавливаем значения полей
        state.setLatitude(56);
        state.setLongitude(74);
        state.setAutoId("o567gfd");
        state.setTime(System.currentTimeMillis());
        state.setSpeed(95.3);
        state.setAzimuth(89.1);
        // Переводим объект в строку json и проверяем ее корректность
        assertTrue(state.toJson().contains("\"latitude\":56"));
        assertTrue(state.toJson().contains("\"longitude\":74"));
        assertTrue(state.toJson().contains("\"autoId\":\"o567gfd\""));
        assertTrue(state.toJson().contains("\"time\":"));
        assertTrue(state.toJson().contains("\"speed\":95.3"));
        assertTrue(state.toJson().contains("\"azimuth\":89.1"));
        // Выводим строку на экран
        System.out.println(state.toJson());
    }

    /**
     * Из строки в объект
     */
    @Test
    public void decodeDTO() throws Exception {
        // Переводим строку json в объект класса State
        ObjectMapper mapper = new ObjectMapper();
        State dto = mapper.readValue(json, State.class);
        // Проверяем значения полей
        assertEquals(56, dto.getLatitude(), 0);
        assertEquals(74, dto.getLongitude(), 0);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1521964156074L, dto.getTime());
        assertEquals(95.3, dto.getSpeed(),0);
        assertEquals(89.1, dto.getAzimuth(),0);
        // Выводим поля объекта на экран
        for(Field field : dto.getClass().getDeclaredFields())
            System.out.println(field.getName());
    }
}
