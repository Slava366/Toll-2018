package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class StateTest {

    private String json = "{\"latitude\":56.0,\"longitude\":74.0,\"autoId\":\"o567gfd\",\"time\":1521964156074,\"speed\":0.0,\"azimuth\":0.0}";
    private String autoId = "o567gfd";

    /**
     * Из объетка в строку
     */
    @Test
    public void encodeDTO() throws Exception {
        State state = new State();
        state.setLatitude(56);
        state.setLongitude(74);
        state.setAutoId("o567gfd");
        state.setTime(System.currentTimeMillis());
        assertTrue(state.toJson().contains("\"latitude\":56"));
        assertTrue(state.toJson().contains("\"time\":"));
        System.out.println(state.toJson());
    }

    /**
     * Из строки в объект
     */
    @Test
    public void decodeDTO() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        State dto = mapper.readValue(json, State.class);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1521964156074L, dto.getTime());
    }

    @Test
    public void getLatitude() {
        State state = new State();
        state.setLatitude(89);
        assertEquals(89, state.getLatitude(), 0);
    }

    @Test
    public void setLatitude() {
        State state = new State();
        state.setLatitude(89);
        assertEquals(89, state.getLatitude(), 0);
    }

    @Test
    public void getLongitude() {
        State state = new State();
        state.setLongitude(89);
        assertEquals(89, state.getLongitude(), 0);
    }

    @Test
    public void setLongitude() {
        State state = new State();
        state.setLongitude(89);
        assertEquals(89, state.getLongitude(), 0);
    }

    @Test
    public void getAutoId() {
        State state = new State();
        state.setAutoId("a666dy");
        assertEquals("a666dy", state.getAutoId());
        assertNotNull(state.getAutoId());
    }

    @Test
    public void setAutoId() {
        State state = new State();
        state.setAutoId("a666dy");
        assertEquals("a666dy", state.getAutoId());
        assertNotNull(state.getAutoId());
    }

    @Test
    public void setTime() {
        State state = new State();
        state.setTime(3265487946L);
        assertEquals(3265487946L, state.getTime());
        assertNotEquals(6548972L, state.getTime());
    }

    @Test
    public void getTime() {
        State state = new State();
        state.setTime(3265487946L);
        assertEquals(3265487946L, state.getTime());
        assertNotEquals(6548972L, state.getTime());
    }

    @Test
    public void getSpeed() {
        State state = new State();
        state.setSpeed(200);
        assertEquals(200, state.getSpeed(), 0);
        assertNotEquals(56, state.getSpeed());
    }

    @Test
    public void setSpeed() {
        State state = new State();
        state.setSpeed(200);
        assertEquals(200, state.getSpeed(), 0);
        assertNotEquals(56, state.getSpeed());
    }

    @Test
    public void getAzimuth() {
        State state = new State();
        state.setAzimuth(98);
        assertEquals(98, state.getAzimuth(), 0);
        assertNotEquals(31, state.getAzimuth());
    }

    @Test
    public void setAzimuth() {
        State state = new State();
        state.setAzimuth(98);
        assertEquals(98, state.getAzimuth(), 0);
        assertNotEquals(31, state.getAzimuth());
    }

    @Test
    public void toJson() throws JsonProcessingException {
        State state = new State();
        for(Field field : state.getClass().getDeclaredFields()) {
            assertTrue(state.toJson().contains(field.getName()));
        }
    }
}
