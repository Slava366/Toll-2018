package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.*;
import jdev.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Эмулирует значения текущей широты, долготы, азимута и мгновенной скорости (так же как это делают настоящие приборы GPS)
 * Помещает значения широты, долготы, азимута и мгновенной скорости в очередь, предоставляемую Сервисом хранения GPSDataSaveService
 */
@Service
public class GPSDataPeekService {

    private List<Coordinate> coordinates;               // Список координат из файла
    private int index = 0;

    @Autowired
    private GPSDataSaveService gpsDataSaveService;      // Ссылка на сервис хранения

    /**
     * Периодически отправляет координаты в очередь сервиса хранения
     * @throws InterruptedException
     */
    @Scheduled (cron = "${cron.prop}")
    private void peek() throws InterruptedException, JsonProcessingException {
        if(index > coordinates.size() - 1) index = 0;       // Если координаты закончились, начинаем сначала
        Coordinate coordinate = coordinates.get(index);     // Получаем текущуюю координату
        Point point = new Point();
        point.setAutoId("b666ad");
        point.setTime(System.currentTimeMillis());
        point.setLat(coordinate.getLatitude());
        point.setLon(coordinate.getLongitude());
        gpsDataSaveService.add(point.toJson());      // Отправляет данные в сервис хранения
        index++;
    }

    /**
     * Считывает координаты из файла *.kml
     */
    @PostConstruct
    private void readKml() {
        Kml kml = Kml.unmarshal(this.getClass().getClassLoader().getResourceAsStream("bpramfamo_75785.kml"));
        Document document = (Document) kml.getFeature();
        Folder folder = (Folder) document.getFeature().get(document.getFeature().size() - 1);
        Placemark placemark = (Placemark) folder.getFeature().get(folder.getFeature().size() - 1);
        LineString lineString = (LineString) placemark.getGeometry();
        coordinates = lineString.getCoordinates();
    }
}
