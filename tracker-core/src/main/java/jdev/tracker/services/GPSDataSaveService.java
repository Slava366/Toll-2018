package jdev.tracker.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Предоставляет интерфейс для записи текущих параметров транспорта (GPS),
 * а также для извлечения параметров в том же порядке, в каком они были записаны.
 */
@Service
public class GPSDataSaveService {

    private BlockingDeque<String> queue =  new LinkedBlockingDeque<>();     // Очередь

    /**
     * Добавляет значение в очередь
     * @param data
     * @throws InterruptedException
     */
    public void add(String data) throws InterruptedException {
        System.out.println(data);
        queue.put(data);
    }

    /**
     * Извлекает значение из очереди
     * @return
     * @throws InterruptedException
     */
    public String get() throws InterruptedException {
        return queue.take();
    }
}
