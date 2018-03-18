package jdev.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Отправляет накопленные данные на центральный сервер в формате json
 */
@Service
public class GPSDataSendService {

    @Autowired
    GPSDataSaveService gpsDataSaveService;           // Ссылка на сервис хранения
}
