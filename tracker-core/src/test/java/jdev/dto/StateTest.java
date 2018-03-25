package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateTest {

    private String json = "{\"latitude\":56.0,\"longitude\":74.0,\"autoId\":\"o567gfd\",\"time\":1521964156074,\"speed\":0.0,\"azimuth\":0.0}";
    private String autoId = "o567gfd";

    /**
     * Из объетка в строку
     */
    @Test
    public void encodeDto() throws Exception {
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
}
