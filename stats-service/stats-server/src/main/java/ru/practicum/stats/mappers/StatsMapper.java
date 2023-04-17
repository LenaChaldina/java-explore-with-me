package ru.practicum.stats.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.StatsDto;
import ru.practicum.StatsUserDto;
import ru.practicum.stats.models.Stats;

@UtilityClass
public class StatsMapper {
    public static Stats toStats(StatsDto requestStatsDto) {


        return Stats.builder()
                .app(requestStatsDto.getApp())
                .ip(requestStatsDto.getIp())
                .uri(requestStatsDto.getUri())
                .timestamp(requestStatsDto.getTimestamp())
                .build();
    }

    public static StatsDto toStatsDto(Stats stats) {
        return StatsDto.builder()
                .id(stats.getId())
                .app(stats.getApp())
                .ip(stats.getIp())
                .uri(stats.getUri())
                .timestamp(stats.getTimestamp())
                .build();
    }

    public static StatsUserDto toUserDto(String app, String uri, Integer hits) {
        return StatsUserDto.builder()
                .app(app)
                .uri(uri)
                .hits(hits)
                .build();
    }
}
