package com.example.forextracker.repository;

import com.example.forextracker.model.ForexRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ForexRateRepository extends JpaRepository<ForexRate, Long> {
    List<ForexRate> findByCurrencyPairAndDateBetween(String currencyPair, LocalDate startDate, LocalDate endDate);
    ForexRate findTopByCurrencyPairAndDateOrderByDateDesc(String currencyPair, LocalDate date);
}
