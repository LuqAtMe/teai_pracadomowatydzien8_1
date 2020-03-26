package home.application.teai_pracadomowatydzien8_1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class WeatherForDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private Integer weoid;
    private Double temperature;
    private LocalDateTime time;

    public WeatherForDatabase() {
    }

    public WeatherForDatabase(String cityName, Integer weoid, Double temperature, LocalDateTime time) {
        this.cityName = cityName;
        this.weoid = weoid;
        this.temperature = temperature;
        this.time = time;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeoid() {
        return weoid;
    }

    public void setWeoid(Integer location) {
        this.weoid = location;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherDatabase{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", weoid=" + weoid +
                ", temperature=" + temperature +
                ", time=" + time +
                '}';
    }
}
