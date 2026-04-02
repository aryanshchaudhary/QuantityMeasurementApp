package com;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;

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

	private double convertToBaseUnit() {
		return unit.toInches(value);
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