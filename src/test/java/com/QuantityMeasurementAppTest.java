package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	@Test
	void testFeetEquality_SameValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

		assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
	}

	@Test
	void testFeetEquality_DifferentValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

		assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
	}

	@Test
	void testFeetEquality_NullComparison() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertFalse(f1.equals(null), "Feet should not be equal to null");
	}

	@Test
	void testFeetEquality_SameReference() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertTrue(f1.equals(f1), "Object should be equal to itself");
	}

	@Test
	void testFeetEquality_TypeSafety() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

		assertFalse(f1.equals("Not a Feet Object"), "Feet should not equal different type");
	}

	@Test
	void testInchesEquality_SameValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(5.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(5.0);

		assertTrue(i1.equals(i2), "5.0 inch should be equal to 5.0 inch");
	}

	@Test
	void testInchesEquality_DifferentValue() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(5.0);
		QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(6.0);

		assertFalse(i1.equals(i2), "5.0 inch should not be equal to 6.0 inch");
	}

	@Test
	void testInchesEquality_NullComparison() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(5.0);

		assertFalse(i1.equals(null), "Inches should not be equal to null");
	}

	@Test
	void testInchesEquality_SameReference() {
		QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(5.0);

		assertTrue(i1.equals(i1), "Object should be equal to itself");
	}

	@Test
	void testFeetAndInchesEquality_True() {
		assertTrue(QuantityMeasurementApp.checkFeetAndInchesEquality(1.0, 12.0), "1 ft should equal 12 inches");
	}

	@Test
	void testFeetAndInchesEquality_False() {
		assertFalse(QuantityMeasurementApp.checkFeetAndInchesEquality(1.0, 10.0), "1 ft should not equal 10 inches");
	}

	@Test
	void testFeet_NonNumericInput() {
		assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Feet(Double.NaN));
	}

	@Test
	void testInches_NonNumericInput() {
		assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Inches(Double.NaN));
	}

	@Test
	void testLength_FeetToInches_Equivalent() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_SameUnit() {
		Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_DifferentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);

		assertFalse(l1.equals(l2));
	}

	@Test
	void testLength_InvalidUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
	}

	@Test
	void testEquality_YardToYard_SameValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToFeet_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(3.0, Length.LengthUnit.FEET);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToInches_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(36.0, Length.LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_CentimetersToInches_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length feet = new Length(3.0, Length.LengthUnit.FEET);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}
}