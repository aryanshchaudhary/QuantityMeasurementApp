package com;

public class QuantityMeasurementApp {

	public static class Feet {

		private final double value;

		public Feet(double value) {
			validate(value);
			this.value = value;
		}

		private void validate(double value) {
			if (Double.isNaN(value) || Double.isInfinite(value)) {
				throw new IllegalArgumentException("Feet value must be numeric");
			}
		}

		public double getValue() {
			return value;
		}

		public double toInches() {
			return value * 12;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			Feet other = (Feet) obj;

			return Double.compare(this.value, other.value) == 0;
		}

		@Override
		public int hashCode() {
			return Double.hashCode(value);
		}
	}

	public static class Inches {

		private final double value;

		public Inches(double value) {
			validate(value);
			this.value = value;
		}

		private void validate(double value) {
			if (Double.isNaN(value) || Double.isInfinite(value)) {
				throw new IllegalArgumentException("Inches value must be numeric");
			}
		}

		public double getValue() {
			return value;
		}

		public double toFeet() {
			return value / 12;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			Inches other = (Inches) obj;

			return Double.compare(this.value, other.value) == 0;
		}

		@Override
		public int hashCode() {
			return Double.hashCode(value);
		}
	}

	public static boolean checkFeetEquality(double value1, double value2) {
		Feet f1 = new Feet(value1);
		Feet f2 = new Feet(value2);
		return f1.equals(f2);
	}

	public static boolean checkInchesEquality(double value1, double value2) {
		Inches i1 = new Inches(value1);
		Inches i2 = new Inches(value2);
		return i1.equals(i2);
	}

	public static boolean checkFeetAndInchesEquality(double feet, double inches) {
		Feet f = new Feet(feet);
		Inches i = new Inches(inches);
		return Double.compare(f.toInches(), i.getValue()) == 0;
	}
	
	public static void demonstrateLengthConversion(double value, LengthUnit from, 
			LengthUnit to) {
		Quantity<LengthUnit> q = new Quantity<>(value, from);
	    Quantity<LengthUnit> result = q.convertTo(to);
		
	    System.out.println("convert(" + value + ", " + from + ", " + to + ") -> " + result);
	}
	
	public static void demonstrateLengthConversion(Quantity<LengthUnit> length,
			LengthUnit target) {
		Quantity<LengthUnit> converted = length.convertTo(target);
		
		System.out.println(length + "-> " + converted);
	}
	
	//UC 6
	public static void demonstrateAddition(Quantity<LengthUnit> l1, Quantity<LengthUnit> l2) {
		Quantity<LengthUnit> result = l1.add(l2);
		System.out.println("add(" + l1 + ", " + l2 + ") -> " + result);
	}
	
	//UC 7
	public static void demonstrateAdditionWithTargetUnit(Quantity<LengthUnit> l1, Quantity<LengthUnit> l2, LengthUnit target) {
		Quantity<LengthUnit> result = l1.add(l2, target);
	    System.out.println("add(" + l1 + ", " + l2 + ", " + target + ") → " + result);
	}
	
	
	// UC12 
	//Subtraction
	public static void demonstrateSubtraction(Quantity<? extends IMeasurable> q1,
	                                          Quantity<? extends IMeasurable> q2) {

	    Quantity<?> result = q1.subtract((Quantity) q2);

	    System.out.println("subtract(" + q1 + ", " + q2 + ") -> " + result);
	}

	// Division
	public static void demonstrateDivision(Quantity<? extends IMeasurable> q1,
	                                       Quantity<? extends IMeasurable> q2) {

	    double result = q1.divide((Quantity) q2);

	    System.out.println("divide(" + q1 + ", " + q2 + ") -> " + result);
	}

	public static void main(String[] args) {

		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equal (" + checkFeetEquality(1.0, 1.0) + ")");

		System.out.println("Input: 1.0 ft and 2.0 ft");
		System.out.println("Output: Equal (" + checkFeetEquality(1.0, 2.0) + ")");

		System.out.println("\nInput: 1.0 inch and 1.0 inch");
		System.out.println("Output: Equal (" + checkInchesEquality(1.0, 1.0) + ")");

		System.out.println("\nInput: 1.0 ft and 12.0 inch");
		System.out.println("Output: Equal (" + checkFeetAndInchesEquality(1.0, 12.0) + ")");
		
		Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("UC3 (Generic Length): " + l1.equals(l2));
		
		Quantity<LengthUnit> y1 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> f1 = new Quantity<>(3.0, LengthUnit.FEET);
		System.out.println("1 Yard == 3 Feet → " + y1.equals(f1));

		Quantity<LengthUnit> y2 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> i1 = new Quantity<>(36.0, LengthUnit.INCHES);
		System.out.println("1 Yard == 36 Inches → " + y2.equals(i1));

		Quantity<LengthUnit> cm1 = new Quantity<>(1.0, LengthUnit.CENTIMETERS);
		Quantity<LengthUnit> inch1 = new Quantity<>(0.393701, LengthUnit.INCHES);
		System.out.println("1 cm == 0.393701 inch → " + cm1.equals(inch1));
		
		demonstrateLengthConversion(1.0, LengthUnit.FEET,
				LengthUnit.INCHES);
		
		demonstrateLengthConversion(3.0, LengthUnit.YARDS,
		        LengthUnit.FEET);

		demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS,
		        LengthUnit.INCHES);
		
		// UC 6
		demonstrateAddition(
		        new Quantity<>(1.0, LengthUnit.FEET),
		        new Quantity<>(12.0, LengthUnit.INCHES));

		demonstrateAddition(
		        new Quantity<>(1.0, LengthUnit.YARDS),
		        new Quantity<>(3.0, LengthUnit.FEET));

		demonstrateAddition(
		        new Quantity<>(5.0, LengthUnit.FEET),
		        new Quantity<>(-2.0, LengthUnit.FEET));
		
		//UC 7
		demonstrateAdditionWithTargetUnit(
		        new Quantity<>(1.0, LengthUnit.FEET),
		        new Quantity<>(12.0, LengthUnit.INCHES),
		        LengthUnit.FEET);

		demonstrateAdditionWithTargetUnit(
		        new Quantity<>(1.0, LengthUnit.FEET),
		        new Quantity<>(12.0, LengthUnit.INCHES),
		        LengthUnit.INCHES);

		demonstrateAdditionWithTargetUnit(
		        new Quantity<>(1.0, LengthUnit.FEET),
		        new Quantity<>(12.0, LengthUnit.INCHES),
		        LengthUnit.YARDS);
		
		//UC 9
		Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("Weight equality: " + w1.equals(w2));

		Quantity<WeightUnit> converted = w1.convertTo(WeightUnit.GRAM);
		System.out.println("Convert 1 kg to gram → " + converted);

		Quantity<WeightUnit> sum = w1.add(w2);
		System.out.println("Addition (kg + g) → " + sum);

		Quantity<WeightUnit> sumTarget =
		        w1.add(w2, WeightUnit.GRAM);

		System.out.println("Addition with target unit → " + sumTarget);
		
		// UC 11 
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

		System.out.println("\nVolume equality (1L == 1000mL): " + v1.equals(v2));

		Quantity<VolumeUnit> convertedVolume = v1.convertTo(VolumeUnit.MILLILITRE);
		System.out.println("Convert 1L to mL → " + convertedVolume);

		Quantity<VolumeUnit> sumVolume = v1.add(v2);
		System.out.println("Addition (L + mL) → " + sumVolume);

		Quantity<VolumeUnit> sumTargetVolume =
		        v1.add(v3, VolumeUnit.MILLILITRE);

		System.out.println("Addition with target unit → " + sumTargetVolume);
		
		// UC12
		

		Quantity<LengthUnit> s1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> s2 = new Quantity<>(6.0, LengthUnit.INCHES);

		System.out.println("Subtraction (Feet - Inches): " + s1.subtract(s2));

		Quantity<WeightUnit> wSub1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> wSub2 = new Quantity<>(5000.0, WeightUnit.GRAM);

		System.out.println("Subtraction (kg - g): " + wSub1.subtract(wSub2));

		Quantity<VolumeUnit> vSub1 = new Quantity<>(5.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> vSub2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

		System.out.println("Subtraction (L - mL): " + vSub1.subtract(vSub2));

		Quantity<LengthUnit> d1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> d2 = new Quantity<>(2.0, LengthUnit.FEET);

		System.out.println("Division (Feet/Feet): " + d1.divide(d2));

		Quantity<WeightUnit> d3 = new Quantity<>(2000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> d4 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		System.out.println("Division (Gram/Kg): " + d3.divide(d4));

		Quantity<VolumeUnit> d5 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> d6 = new Quantity<>(1.0, VolumeUnit.LITRE);

		System.out.println("Division (mL/L): " + d5.divide(d6));
	}
}