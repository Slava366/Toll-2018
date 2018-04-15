package jdev.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class GPSContextTest {

    @Test
    public void gpsDataPeekService() {
        GPSContext context = new GPSContext();
        assertNotNull(context.gpsDataPeekService());
    }

    @Test
    public void gpsDataSendService() {
        GPSContext context = new GPSContext();
        assertNotNull(context.gpsDataSendService());
    }

    @Test
    public void gpsDataSaveService() {
        GPSContext context = new GPSContext();
        assertNotNull(context.gpsDataSaveService());
    }

    @Test
    public void poolScheduler() {
        GPSContext context = new GPSContext();
        assertNotNull(context.poolScheduler());
    }
}