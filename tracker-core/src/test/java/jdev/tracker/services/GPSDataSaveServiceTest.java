package jdev.tracker.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class GPSDataSaveServiceTest {

    @Test
    public void addAndGet() throws InterruptedException {
        // Создаем объект класса GPSDataSaveService
        GPSDataSaveService saveService = new GPSDataSaveService();
        // Добавляем данные в очередь
        saveService.add("test string1");
        saveService.add("test string2");
        // Считываем данные из очереди в том же порядке
        assertEquals("test string1", saveService.get());
        assertEquals("test string2", saveService.get());
    }
}