package home.application.teai_pracadomowatydzien8_1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherDatabase, Long> {
}
