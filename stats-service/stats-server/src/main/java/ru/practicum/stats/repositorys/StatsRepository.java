package ru.practicum.stats.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.models.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
    List<Stats> findAllByTimestampIsBetween(LocalDateTime start, LocalDateTime end);

    @Query("select s from Stats s where s.uri in :uris and s.timestamp between :start and :end ")
    List<Stats> findAllBetweenDates(List<String> uris, LocalDateTime start, LocalDateTime end);
}
