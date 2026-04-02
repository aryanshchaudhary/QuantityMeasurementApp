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

	public static void main(String[] args) {

		System.out.println("Input: 1.0 ft and 1.0 ft");
		System.out.println("Output: Equal (" + checkFeetEquality(1.0, 1.0) + ")");

		System.out.println("Input: 1.0 ft and 2.0 ft");
		System.out.println("Output: Equal (" + checkFeetEquality(1.0, 2.0) + ")");

		System.out.println("\nInput: 1.0 inch and 1.0 inch");
		System.out.println("Output: Equal (" + checkInchesEquality(1.0, 1.0) + ")");

		System.out.println("\nInput: 1.0 ft and 12.0 inch");
		System.out.println("Output: Equal (" + checkFeetAndInchesEquality(1.0, 12.0) + ")");
		
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		System.out.println("UC3 (Generic Length): " + l1.equals(l2));
	}
}