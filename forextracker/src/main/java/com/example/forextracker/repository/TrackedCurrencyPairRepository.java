package com.example.forextracker.repository;

import com.example.forextracker.model.TrackedCurrencyPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackedCurrencyPairRepository extends JpaRepository<TrackedCurrencyPair, Long> {
    boolean existsByCurrencyPair(String currencyPair);
}
