package com;
import java.util.Objects;

public class QuantityWeight {
	private final double value;
	private final WeightUnit unit;
	
	public QuantityWeight(double value, WeightUnit unit) {
		if(!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite");
		}
		
		if(unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		
		this.value = value;
		this.unit = unit;
	}
	
	public double getValue() {
		return value;
	}
	
	public WeightUnit getUnit() {
		return unit;
	}
	
	public double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || getClass() != obj.getClass()) return false;
		
		QuantityWeight other = (QuantityWeight) obj;
		
		double diff = Math.abs(
				this.convertToBaseUnit() -
				other.convertToBaseUnit()
				);
		return diff < 1e-3;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(convertToBaseUnit());
	}
	
	public QuantityWeight convertTo(WeightUnit targetUnit) {

        double base = unit.convertToBaseUnit(value);

        double result = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(result, targetUnit);
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2) {

        double base1 = w1.unit.convertToBaseUnit(w1.value);
        double base2 = w2.unit.convertToBaseUnit(w2.value);

        double sum = base1 + base2;

        double result = w1.unit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, w1.unit);
    }

    public static QuantityWeight add(
            QuantityWeight w1,
            QuantityWeight w2,
            WeightUnit targetUnit) {

        double base1 = w1.unit.convertToBaseUnit(w1.value);
        double base2 = w2.unit.convertToBaseUnit(w2.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
