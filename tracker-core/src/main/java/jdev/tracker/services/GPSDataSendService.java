package jdev.tracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Отправляет накопленные данные на центральный сервер в формате json
 */
@Service
public class GPSDataSendService {

    private static final Logger LOG = LoggerFactory.getLogger(GPSDataSendService.class);
    private static final String SERVER_CORE_STATE_URL = "http://localhost:8080/state";      // Адрес сервера

    @Autowired
    GPSDataSaveService gpsDataSaveService;           // Ссылка на сервис хранения


    /**
     * Считывает значение из очереди, если есть
     * Отправляет на сервер
     */
    @Scheduled (fixedDelayString = "${sendFixedDelay.prop}", initialDelayString = "${sendInitialDelay.prop}")
    private void send() throws InterruptedException {
        String gpsData = gpsDataSaveService.get();
        // Подготавливаем заголовок для отправки на сервер
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("stateInfo", gpsData);
        // Отправляем строку на сервер
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Пробуем отправить запрос
            String answer = restTemplate.postForObject(SERVER_CORE_STATE_URL, httpHeaders, String.class);
            // Проверяем ответ сервера
            if(answer.contains("\"success\":true")) LOG.info(answer + " FOR: " + gpsData);
            else LOG.warn(answer + " FOR: " + gpsData);
        } catch (Exception e) {
            // Ошибка при запросе
            LOG.error(e.getMessage() + " FOR: " + gpsData);
        }
    }
}
