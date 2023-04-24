package ru.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.StatsDto;
import ru.practicum.StatsUserDto;
import ru.practicum.stats.mappers.StatsMapper;
import ru.practicum.stats.models.Stats;
import ru.practicum.stats.repositorys.StatsRepository;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServerServiceImpl implements StatsServerService {

    private final StatsRepository statsRepository;

    @Override
    public StatsDto saveStats(StatsDto requestStatsDto) {
        Stats stats = StatsMapper.toStats(requestStatsDto);
        return StatsMapper.toStatsDto(statsRepository.save(stats));
    }

    @Override
    public List<StatsUserDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        List<Stats> stats;
        if (uris != null) {
            stats = statsRepository.findAllByUriInAndTimestampBetween(uris, start, end);
        } else {
            stats = statsRepository.findAllByTimestampIsBetween(start, end);
        }
        if (unique) {
            List<Stats> uniqueStats = new ArrayList<>();
            List<String> ips = new ArrayList<>();
            for (Stats stat : stats) {
                String ip = stat.getIp();
                if (!ips.contains(ip)) {
                    uniqueStats.add(stat);
                    ips.add(ip);
                }
            }
            stats = uniqueStats;
        }
        return getStatsUserDtos(stats);
    }

    private List<StatsUserDto> getStatsUserDtos(List<Stats> stats) {
        List<StatsUserDto> dtos = new ArrayList<>();
        List<String> uris = new ArrayList<>();
        for (Stats stat : stats) {
            String uri = stat.getUri();
            if (!uris.contains(uri)) {
                dtos.addAll(getStat(stats, uri));
                uris.add(uri);
            }
        }
        dtos.sort(Comparator.comparingInt(StatsUserDto::getHits).reversed());
        return dtos;
    }

    private List<StatsUserDto> getStat(List<Stats> stats, String uri) {
        List<StatsUserDto> userStats = new ArrayList<>();
        Map<String, Integer> numbers = count(stats, uri);
        for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
            userStats.add(StatsMapper.toUserDto(entry.getKey(), uri, entry.getValue()));
        }
        return userStats;
    }

    private Map<String, Integer> count(List<Stats> stats, String uri) {
        Map<String, Integer> numbers = new HashMap<>();
        for (Stats stat : stats) {
            if (Objects.equals(stat.getUri(), uri)) {
                String app = stat.getApp();
                if (numbers.containsKey(app)) {
                    int number = numbers.get(app);
                    numbers.put(app, number + 1);
                } else {
                    numbers.put(app, 1);
                }
            }
        }
        return numbers;
    }
}
