package ru.practicum.stats.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.models.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
    List<Stats> findAllByTimestampIsBetween(LocalDateTime start, LocalDateTime end);

    List<Stats> findAllByUriInAndTimestampBetween(List uris, LocalDateTime start, LocalDateTime end);
}
