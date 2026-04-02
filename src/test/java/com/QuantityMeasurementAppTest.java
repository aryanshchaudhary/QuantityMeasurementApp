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
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_SameUnit() {
		Length l1 = new Length(1.0, LengthUnit.INCHES);
		Length l2 = new Length(1.0, LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	@Test
	void testLength_DifferentValue() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(2.0, LengthUnit.FEET);

		assertFalse(l1.equals(l2));
	}

	@Test
	void testLength_InvalidUnit() {
		assertThrows(IllegalArgumentException.class, () -> new Length(1.0, null));
	}

	@Test
	void testEquality_YardToYard_SameValue() {
		Length l1 = new Length(1.0, LengthUnit.YARDS);
		Length l2 = new Length(1.0, LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToFeet_EquivalentValue() {
		Length l1 = new Length(1.0, LengthUnit.YARDS);
		Length l2 = new Length(3.0, LengthUnit.FEET);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_YardToInches_EquivalentValue() {
		Length l1 = new Length(1.0, LengthUnit.YARDS);
		Length l2 = new Length(36.0, LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_CentimetersToInches_EquivalentValue() {
		Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
		Length l2 = new Length(0.393701, LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}

	@Test
	void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, LengthUnit.YARDS);
		Length feet = new Length(3.0, LengthUnit.FEET);
		Length inches = new Length(36.0, LengthUnit.INCHES);

		assertTrue(yard.equals(feet));
		assertTrue(feet.equals(inches));
		assertTrue(yard.equals(inches));
	}
	
	// UC-5 
	
	@Test
	void testConversion_FeetToInches() {
		double result = Length.convert(1.0,
				 LengthUnit.FEET,
		            LengthUnit.INCHES);

		    assertEquals(12.0, result, 1e-6);
	}

	@Test
	void testConversion_YardsToFeet() {
	    double result = Length.convert(3.0,
	            LengthUnit.YARDS,
	            LengthUnit.FEET);

	    assertEquals(9.0, result, 1e-6);
	}
	
	@Test
	void testConversion_CentimetersToInches() {
	    double result = Length.convert(2.54,
	            LengthUnit.CENTIMETERS,
	            LengthUnit.INCHES);

	    assertEquals(1.0, result, 1e-6);
	}
	
	@Test
	void testConversion_SameUnit() {
	    double result = Length.convert(5.0,
	            LengthUnit.FEET,
	            LengthUnit.FEET);

	    assertEquals(5.0, result, 1e-6);
	}
	
	@Test
	void testConversion_InvalidInput() {

	    assertThrows(IllegalArgumentException.class,
	            () -> Length.convert(1.0, null,
	                    LengthUnit.FEET));

	    assertThrows(IllegalArgumentException.class,
	            () -> Length.convert(Double.NaN,
	                    LengthUnit.FEET,
	                    LengthUnit.INCHES));
	}
	
	//UC 6
	@Test
	void testAddition_SameUnit() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(2.0, LengthUnit.FEET);

	    Length result = Length.add(l1, l2);

	    assertEquals(3.0, result.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_CrossUnit_FeetPlusInches() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length result = Length.add(l1, l2);

	    assertEquals(2.0, result.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_Commutativity() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length r1 = Length.add(l1, l2);
	    Length r2 = Length.add(l2, l1);

	    assertEquals(
	            r1.convertTo(LengthUnit.INCHES).getValue(),
	            r2.convertTo(LengthUnit.INCHES).getValue(),
	            1e-6
	    );
	}
	
	@Test
	void testAddition_WithZero() {
	    Length l1 = new Length(5.0, LengthUnit.FEET);
	    Length zero = new Length(0.0, LengthUnit.INCHES);

	    Length result = Length.add(l1, zero);

	    assertEquals(5.0, result.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_NullOperand() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);

	    assertThrows(IllegalArgumentException.class,
	            () -> Length.add(l1, null));
	}
	
	@Test
	void testAddition_TargetUnit_Feet() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length result = Length.add(l1, l2, LengthUnit.FEET);

	    assertEquals(2.0, result.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_TargetUnit_Inches() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length result = Length.add(l1, l2, LengthUnit.INCHES);

	    assertEquals(24.0, result.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_TargetUnit_Yards() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length result = Length.add(l1, l2, LengthUnit.YARDS);

	    assertEquals(0.667, result.getValue(), 1e-3);
	}
	
	@Test
	void testAddition_TargetUnit_Commutative() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    Length r1 = Length.add(l1, l2, LengthUnit.YARDS);
	    Length r2 = Length.add(l2, l1, LengthUnit.YARDS);

	    assertEquals(r1.getValue(), r2.getValue(), 1e-6);
	}
	
	@Test
	void testAddition_TargetUnit_Null() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    assertThrows(IllegalArgumentException.class,
	            () -> Length.add(l1, l2, null));
	}
	
	//UC 9
	private static final double EPSILON = 1e-6;
	
	 //Equality Tests

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.204624, WeightUnit.POUND);

        assertTrue(w1.equals(w2));
    }

    //Conversion Tests

    @Test
    void testConversion_KilogramToGram() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight w = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    //Addition Tests

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = QuantityWeight.add(w1, w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result =
                QuantityWeight.add(w1, w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    //Edge Case Tests

    @Test
    void testEquality_NullComparison() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(w.equals(null));
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityWeight w = new QuantityWeight(0.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

}