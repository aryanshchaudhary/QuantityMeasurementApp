package com.app.historyservice.service;

import com.app.historyservice.entity.CalculationHistory;
import java.util.List;

public interface IHistoryService {

    CalculationHistory saveHistory(CalculationHistory history);

    List<CalculationHistory> getUserHistory(String name);
}