package jdev.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointTest {

    private String expected = "{\"lat\":56.0,\"lon\":74.0,\"autoId\":\"o567gfd\",\"time\":1520422523797}";
    private String autoId = "o567gfd";

    @Test
    /**
     * Из объетка в строку
     */
    public void toJson() throws Exception {
        Point point = new Point();
        point.setLat(56);
        point.setLon(74);
        point.setAutoId("o567gfd");
        point.setTime(System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"lat\":56"));
        assertTrue(point.toJson().contains("\"time\":"));
        System.out.println(point.toJson());
    }

    @Test
    /**
     * Из строки в объект
     */
    public void decodeDTO() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Point dto = mapper.readValue(expected, Point.class);
        assertEquals(autoId, dto.getAutoId());
        assertEquals(1520422523797L, dto.getTime());
    }
}
