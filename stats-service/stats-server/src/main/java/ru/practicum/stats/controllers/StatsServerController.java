package ru.practicum.stats.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatsDto;
import ru.practicum.StatsUserDto;
import ru.practicum.stats.service.StatsServerService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StatsServerController {
    private final StatsServerService statsServerService;

    @PostMapping("/hit")
    public StatsDto save(@RequestBody StatsDto requestStatsDto) {
        return statsServerService.saveStats(requestStatsDto);
    }

    @GetMapping("/stats")
    public List<StatsUserDto> get(@RequestParam(name = "start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                  @RequestParam(name = "end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                  @RequestParam(name = "uris", required = false) List<String> uris,
                                  @RequestParam(name = "unique", defaultValue = "false") Boolean unique) {
        return statsServerService.getStats(start, end, uris, unique);
    }
}
