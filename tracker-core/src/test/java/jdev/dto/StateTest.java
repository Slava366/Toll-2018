package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateTest {

    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1520422523797}";
    private String autoId = "o567gfd";

    @Test
    /**
     * Из объетка в строку
     */
    public void toJson() throws Exception {
        State state = new State();
        state.setLatitude(56);
        state.setLongitude(74);
        state.setAutoId("o567gfd");
        state.setTime(System.currentTimeMillis());
        assertTrue(state.toJson().contains("\"lat\":56"));
        assertTrue(state.toJson().contains("\"time\":"));
        System.out.println(state.toJson());
    }

    @Test
    /**
     * Из строки в объект
     */
    public void decodeDTO() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        State dto = mapper.readValue(expected, State.class);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1520422523797L, dto.getTime());
    }
}
