package com.app.quantitymeasurement.entity;

public class QuantityDTO {

    private double value;
    private String unit;

    public QuantityDTO() {
    }

    public QuantityDTO(double value, String unit) {
        this.value = value;
        setUnit(unit);
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
        this.unit = unit.toUpperCase().trim();
    }
    
    @Override
    public String toString() {
        return "Quantity(" + value + " " + unit + ")";
    }
}