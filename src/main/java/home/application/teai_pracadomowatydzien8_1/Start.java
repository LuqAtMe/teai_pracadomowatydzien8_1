package home.application.teai_pracadomowatydzien8_1;

import home.application.teai_pracadomowatydzien8_1.JsonFromWeb.Location;
import home.application.teai_pracadomowatydzien8_1.JsonFromWeb.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@EnableScheduling
public class Start {

    private final String CITY = "Warsaw";

    private WeatherRepo weatherRepo;

    @Autowired
    public Start(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    //@Scheduled(fixedDelay = 10000) // get Weather info in 10 sec intervals
    @Scheduled(cron = "0 0 * * * *") // get Weather info in 1 h intervals
    public void init() {

        Location selectedCity = getWeoid(CITY);
        Weather weatherFromJson = getWeather(selectedCity.getWoeid());

        WeatherDatabase weatherDatabase = new WeatherDatabase(
                selectedCity.getTitle(),
                selectedCity.getWoeid(),
                weatherFromJson.getConsolidatedWeather().get(0).getTheTemp(),
                LocalDateTime.now()
        );

        weatherRepo.save(weatherDatabase);
        System.out.println(weatherDatabase.toString());
    }

    public Location getWeoid(String city) {
        String url = "https://www.metaweather.com/api/location/search/?query=" + city;

        Location location = new Location();
        HttpEntity httpEntity = new HttpEntity(location);

        RestTemplate restTemplateCity = new RestTemplate();
        ResponseEntity<Location[]> exchange = restTemplateCity.exchange(url, HttpMethod.GET, httpEntity, Location[].class);
        Location firstCity = Arrays.stream(exchange.getBody()).findFirst().get();
        return firstCity;

    }

    public Weather getWeather(Integer weoid) {
        Weather weather = new Weather();
        HttpEntity httpEntity = new HttpEntity(weather);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.metaweather.com/api/location/" + weoid + "/";

        ResponseEntity<Weather> weatherFromWeb = restTemplate.exchange(url,
                HttpMethod.GET,
                httpEntity,
                Weather.class);

        Weather firstWeather = weatherFromWeb.getBody();
        return firstWeather;
    }


}
