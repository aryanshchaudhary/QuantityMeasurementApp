package com.app.historyservice.service;

import com.app.historyservice.entity.CalculationHistory;
import com.app.historyservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryRepository repository;

    @Override
    public CalculationHistory saveHistory(CalculationHistory history) {
        history.setTimestamp(LocalDateTime.now());
        return repository.save(history);
    }

    @Override
    public List<CalculationHistory> getUserHistory(String name) {
        return repository.findByName(name);
    }
}