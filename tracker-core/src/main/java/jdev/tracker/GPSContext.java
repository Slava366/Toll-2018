package jdev.tracker;

import jdev.tracker.services.GPSDataPeekService;
import jdev.tracker.services.GPSDataSaveService;
import jdev.tracker.services.GPSDataSendService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Связывает работу сервисов:
 * {@link jdev.tracker.services.GPSDataPeekService}
 * {@link jdev.tracker.services.GPSDataSaveService}
 * {@link jdev.tracker.services.GPSDataSendService}
 */
@Configuration
@EnableScheduling
@PropertySource("classpath:app.properties")
public class GPSContext {

    @Bean
    public GPSDataPeekService gpsDataPeekService() {
        return new GPSDataPeekService();
    }

    @Bean
    public GPSDataSendService gpsDataSendService() {
        return new GPSDataSendService();
    }

    @Bean
    public GPSDataSaveService gpsDataSaveService() {
        return new GPSDataSaveService();
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        return scheduler;
    }
}
