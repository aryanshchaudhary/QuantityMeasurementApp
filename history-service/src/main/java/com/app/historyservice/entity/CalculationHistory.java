package com.app.historyservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculation_history")
public class CalculationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String operation;
    private String type;

    private double value1;
    private String unit1;

    private double value2;
    private String unit2;

    private double result;
    private String resultUnit;

    private LocalDateTime timestamp;

    public CalculationHistory() {}

    // ✅ CORRECT GETTERS/SETTERS

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getValue1() { return value1; }
    public void setValue1(double value1) { this.value1 = value1; }

    public String getUnit1() { return unit1; }
    public void setUnit1(String unit1) { this.unit1 = unit1; }

    public double getValue2() { return value2; }
    public void setValue2(double value2) { this.value2 = value2; }

    public String getUnit2() { return unit2; }
    public void setUnit2(String unit2) { this.unit2 = unit2; }

    public double getResult() { return result; }
    public void setResult(double result) { this.result = result; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}