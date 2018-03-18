package jdev.tracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Отправляет накопленные данные на центральный сервер в формате json
 */
@Service
public class GPSDataSendService {

    private static final Logger LOG = LoggerFactory.getLogger(GPSDataSendService.class);

    @Autowired
    GPSDataSaveService gpsDataSaveService;           // Ссылка на сервис хранения


    /**
     * Считывает значение из очереди, если есть
     * Отправляет на сервер (пока в LOG)
     */
    @Scheduled (fixedDelayString = "${sendFixedDelay.prop}", initialDelayString = "${sendInitialDelay.prop}")
    private void send() throws InterruptedException {
        String gpsData = gpsDataSaveService.get();
        LOG.info(gpsData);
    }
}
