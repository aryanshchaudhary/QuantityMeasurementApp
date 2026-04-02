package com;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;
	
	public double getValue() {
		return value;
	}
	
	public LengthUnit getUnit() {
		return unit;
	}

	public enum LengthUnit {
		FEET(12.0), INCHES(1.0), YARDS(36.0), CENTIMETERS(0.393701);

		private final double conversionFactorToInches;

		LengthUnit(double conversionFactorToInches) {
			this.conversionFactorToInches = conversionFactorToInches;
		}

		public double toInches(double value) {
			return value * conversionFactorToInches;
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
	
	//UC 6
	public static Length add(Length l1, Length l2) {
		if(l1 == null || l2 == null) {
			throw new IllegalArgumentException("Operand cannot be null");
		}
		if(!Double.isFinite(l1.value) || !Double.isFinite(l2.value)) {
			throw new IllegalArgumentException("Values must be finite");
		}
		
		double base1 = l1.unit.toInches(l1.value);
	    double base2 = l2.unit.toInches(l2.value);

	    double sumBase = base1 + base2;
	    
	    double resultValue =
	            sumBase / l1.unit.toInches(1.0);

	    return new Length(resultValue, l1.unit);
	}
	public Length add(Length other) {
		return add(this, other);
	}

	private double convertToBaseUnit() {
		return unit.toInches(value);
	}
	
	public static double convert(double value, LengthUnit source, LengthUnit target) {
		if(!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite");
		}
		
		if(source == null || target == null) {
			throw new IllegalArgumentException("Units cannot be null");
		}
		
		double baseValue = source.toInches(value);
		
		return baseValue / target.toInches(1.0);
	}
	
	public Length convertTo(LengthUnit targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		double convertedValue = convert(this.value, this.unit, targetUnit);
		
		return new Length(convertedValue, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Length other = (Length) obj;

		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
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