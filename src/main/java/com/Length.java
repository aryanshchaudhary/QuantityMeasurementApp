package com;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),     
        INCHES(1.0);    
    	
        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be numeric");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Double.compare(
                this.convertToBaseUnit(),
                other.convertToBaseUnit()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return "Length(" + value + ", " + unit + ")";
    }
}