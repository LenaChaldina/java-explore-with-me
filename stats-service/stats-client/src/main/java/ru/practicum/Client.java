package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class Client {
    @Value("${stats-server.uri}")
    private String local;
    private final RestTemplate restTemplate = new RestTemplate();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //сохраняю статистику
    public void save(StatsDto statsDto) {
        restTemplate.postForLocation(local + "/hit", statsDto);
    }

    //получаю статистику
    public List<StatsUserDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        String startFormatter = start.format(dateTimeFormatter);
        String endFormatter = end.format(dateTimeFormatter);

        ResponseEntity<StatsUserDto[]> userStats = restTemplate.getForEntity(local + "/stats?start=" + startFormatter +
                        "&end=" + endFormatter + "&uris=" + uris + "&unique=" + unique,
                StatsUserDto[].class);
        return Arrays.asList(Objects.requireNonNull(userStats.getBody()));
    }
}
