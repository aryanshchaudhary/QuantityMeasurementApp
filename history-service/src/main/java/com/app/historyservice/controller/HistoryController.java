package com.app.historyservice.controller;

import com.app.historyservice.entity.CalculationHistory;
import com.app.historyservice.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private IHistoryService service;

    // Save history
    @PostMapping("/save")
    public CalculationHistory save(@RequestBody CalculationHistory history) {
        return service.saveHistory(history);
    }

    // Get user history
    @GetMapping("/{name}")
    public List<CalculationHistory> getHistory(@PathVariable String name) {
        return service.getUserHistory(name);
    }
}