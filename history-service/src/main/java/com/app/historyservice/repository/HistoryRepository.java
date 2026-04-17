package com.app.historyservice.repository;

import com.app.historyservice.entity.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<CalculationHistory, Long> {

    List<CalculationHistory> findByName(String name);
}