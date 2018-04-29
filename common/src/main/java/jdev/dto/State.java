package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;


/**
 * Описывает состояние объекта
 */
@Entity
@Table(name = "STATES")
public class State {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;
    @Column(name = "LATITUDE")
    private double latitude;        // Широта
    @Column(name = "LONGITUDE")
    private double longitude;       // Долгота
    @Column(name = "AUTO_ID")
    private String autoId;          // Регистрационный знак автомобиля
    @Column(name = "TIME")
    private long time;              // Время
    @Column(name = "SPEED")
    private double speed;           // Скорость, км/ч
    @Column(name = "AZIMUTH")
    private double azimuth;         // Азимут, градусы


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    /**
     * Преобразует обект в строку
     * @return
     * @throws JsonProcessingException
     */
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}