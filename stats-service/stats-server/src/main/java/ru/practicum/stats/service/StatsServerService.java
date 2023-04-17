package ru.practicum.stats.service;

import ru.practicum.StatsDto;
import ru.practicum.StatsUserDto;

import java.util.List;

public interface StatsServerService {
    StatsDto saveStats(StatsDto statsDto);

    List<StatsUserDto> getStats(String start, String end, List<String> uris, boolean unique);
}
