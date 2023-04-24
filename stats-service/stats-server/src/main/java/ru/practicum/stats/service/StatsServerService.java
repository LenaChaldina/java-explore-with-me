package ru.practicum.stats.service;

import ru.practicum.StatsDto;
import ru.practicum.StatsUserDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsServerService {
    StatsDto saveStats(StatsDto statsDto);

    List<StatsUserDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
