package jdev.tracker.services;

import org.springframework.stereotype.Service;

/**
 * Эмулирует значения текущей широты, долготы, азимута и мгновенной скорости (так же как это делают настоящие приборы GPS)
 * Помещает значения широты, долготы, азимута и мгновенной скорости в очередь, предоставляемую Сервисом хранения GPSDataSaveService
 */
@Service
public class GPSDataPeekService {
}
