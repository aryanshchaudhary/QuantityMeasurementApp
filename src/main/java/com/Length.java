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
		
		
		double base1 = l1.getUnit().convertToBaseUnit(l1.getValue());
	    double base2 = l2.getUnit().convertToBaseUnit(l2.getValue());

	    double sumBase = base1 + base2;
	    
	    double result = l1.getUnit().convertFromBaseUnit(sumBase);

	    return new Length(result, l1.getUnit());
	}
	
	// UC 7
	public static Length add(Length l1, Length l2, LengthUnit targetUnit) {

	    if (l1 == null || l2 == null) {
	        throw new IllegalArgumentException("Operands cannot be null");
	    }

	    if (targetUnit == null) {
	        throw new IllegalArgumentException("Target unit cannot be null");
	    }

	    if (!Double.isFinite(l1.getValue()) || !Double.isFinite(l2.getValue())) {
	        throw new IllegalArgumentException("Values must be finite");
	    }

	    double base1 = l1.getUnit().convertToBaseUnit(l1.getValue());
	    double base2 = l2.getUnit().convertToBaseUnit(l2.getValue());

	    double sumBase = base1 + base2;

	    double result = targetUnit.convertFromBaseUnit(sumBase);

	    return new Length(result, targetUnit);
	}
	public Length add(Length other) {
		return add(this, other);
	}

	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}
	
	public static double convert(double value, LengthUnit source, LengthUnit target) {
		if(!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite");
		}
		
		if(source == null || target == null) {
			throw new IllegalArgumentException("Units cannot be null");
		}
		
		double baseValue = source.convertToBaseUnit(value);
		
		return baseValue / target.convertToBaseUnit(1.0);
	}
	
	public Length convertTo(LengthUnit targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		double baseValue = unit.convertToBaseUnit(value);
		
		double converted = targetUnit.convertFromBaseUnit(baseValue);
		
		return new Length(converted, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Length other = (Length) obj;

		double diff = Math.abs(
		        this.convertToBaseUnit() -
		        other.convertToBaseUnit()
		);

		return diff < 1e-6;
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