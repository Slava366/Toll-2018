package jdev.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class GPSContextTest {

    @Test
    public void gpsDataPeekService() {
        // Создаем объект класса GPSContext
        GPSContext context = new GPSContext();
        // Проверяем чтобы метод gpsDataPeekService возвращал не null объект
        assertNotNull(context.gpsDataPeekService());
    }

    @Test
    public void gpsDataSendService() {
        // Создаем объект класса GPSContext
        GPSContext context = new GPSContext();
        // Проверяем чтобы метод gpsDataSendService возвращал не null объект
        assertNotNull(context.gpsDataSendService());
    }

    @Test
    public void gpsDataSaveService() {
        // Создаем объект класса GPSContext
        GPSContext context = new GPSContext();
        // Проверяем чтобы метод gpsDataSaveService возвращал не null объект
        assertNotNull(context.gpsDataSaveService());
    }

    @Test
    public void poolScheduler() {
        // Создаем объект класса GPSContext
        GPSContext context = new GPSContext();
        // Проверяем чтобы метод poolScheduler возвращал не null объект
        assertNotNull(context.poolScheduler());
    }
}