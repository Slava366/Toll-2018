package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.*;
import jdev.dto.State;
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
    private int index = 0;                              // Текущая координата
    private double azimuth;                             // Азимут
    private double speed;                                  // Скорость

    @Autowired
    private GPSDataSaveService gpsDataSaveService;      // Ссылка на сервис хранения

    /**
     * Периодически отправляет координаты в очередь сервиса хранения
     * @throws InterruptedException
     */
    @Scheduled (cron = "${cron.prop}")
    private void peek() throws InterruptedException, JsonProcessingException {
        State state = new State();          // Создаем объект состояния
        state.setAutoId("B666AD");          // Регистрационный номер автомобиля
        state.setTime(System.currentTimeMillis());      // Текущее время
        Coordinate coordinate = coordinates.get(index);     // Получаем текущуюю координату
        state.setLatitude(coordinate.getLatitude());        // Широта
        state.setLongitude(coordinate.getLongitude());      // Долгота
        if(index < coordinates.size() - 1) {    // Есть движение
            getMoreInfo(coordinates.get(index), coordinates.get(index + 1));    // Азимут и скорость
            state.setAzimuth(azimuth);
            state.setSpeed(speed);
            index++;    // Сдвигаем счетчик
        } else {    // Остановка
            getMoreInfo(coordinates.get(index), coordinates.get(index));    // Азимут и скорость
            state.setAzimuth(azimuth);
            state.setSpeed(0);
        }
        gpsDataSaveService.add(state.toJson());      // Отправляет данные в сервис хранения
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

    /**
     * Вычисляет азимут и скорость
     */
    private void getMoreInfo(Coordinate coordinate, Coordinate coordinateNext) {
        final int worldRadius = 6372795;    // Радиус земли
        // Координаты точек в радианах
        double latitude1 = coordinate.getLatitude() * Math.PI / 180;
        double longitude1 = coordinate.getLongitude() * Math.PI / 180;

        double latitude2 = coordinateNext.getLatitude() * Math.PI / 180;
        double longitude2 = coordinateNext.getLongitude() * Math.PI / 180;

        // Косинусы и синусы широт и разницы долгот
        double cosLatitude1 = Math.cos(latitude1);
        double sinLatitude1 = Math.sin(latitude1);
        double cosLatitude2 = Math.cos(latitude2);
        double sinLatitude2 = Math.sin(latitude2);
        double delta = longitude2 - longitude1;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        // Вычисляем длину большого круга
        double y = Math.sqrt(Math.pow(cosLatitude2 * sinDelta, 2) + Math.pow(cosLatitude1 * sinLatitude2 - sinLatitude1 * cosLatitude2 * cosDelta, 2));
        double x = sinLatitude1 * sinLatitude2 + cosLatitude1 * cosLatitude2 * cosDelta;
        double ad = Math.atan2(y, x);
        double distance = ad * worldRadius;     // Метры

        // Вычисление начального азимута
        x = cosLatitude1 * sinLatitude2 - sinLatitude1 * cosLatitude2 * cosDelta;
        y = sinDelta * cosLatitude2;
        double z = Math.toDegrees(Math.atan(- y / x));
        if(x < 0) z += 180;

        double z2 = (z + 180) % 360 - 180;
        z2 = Math.toRadians(z2);
        double angleRadius2 = z2 - ((2 * Math.PI) * Math.floor(z2 / (2 * Math.PI)));
        double angleDegree = (angleRadius2 * 180) / Math.PI;

        // Сохраняем значения
        azimuth = angleDegree;      // Азимут
        speed = distance / 1;      // Скорость м/с
    }
}
