package jdev.tracker.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class GPSDataSaveServiceTest {

    @Test
    public void add() throws InterruptedException {
        GPSDataSaveService saveService = new GPSDataSaveService();
        saveService.add("test string1");
        saveService.add("test string2");
        assertEquals("test string1", saveService.get());
        assertEquals("test string2", saveService.get());
    }

    @Test
    public void get() throws InterruptedException {
        GPSDataSaveService saveService = new GPSDataSaveService();
        saveService.add("test string1");
        saveService.add("test string2");
        assertEquals("test string1", saveService.get());
        assertEquals("test string2", saveService.get());
    }
}