package com.app.quantitymeasurement.unit;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    @Override
    public double getConversionFactor() {
        throw new UnsupportedOperationException("Temperature uses custom conversion");
    }

    @Override
    public double convertToBaseUnit(double value) {
        // Convert everything to Celsius (base)
        if (this == FAHRENHEIT) {
            return (value - 32) * 5 / 9;
        }
        return value;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        // Convert from Celsius
        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5) + 32;
        }
        return baseValue;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public void validateOperationSupport(String operation) {
        // ❌ Temperature does NOT support arithmetic
        if (!operation.equalsIgnoreCase("conversion") &&
            !operation.equalsIgnoreCase("compare")) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation
            );
        }
    }
}