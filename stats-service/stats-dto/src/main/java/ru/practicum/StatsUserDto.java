package ru.practicum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsUserDto {
    private String app;
    private String uri;
    private Integer hits;
}