package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    //LENGTH EQUALITY TESTS

    @Test
    void testLength_FeetToInches_Equivalent() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testLength_SameUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> l2 = new Quantity<>(1.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testLength_DifferentValue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    void testLength_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_YardToYard_SameValue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> l2 = new Quantity<>(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> l2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> l2 = new Quantity<>(36.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> l2 = new Quantity<>(0.393701, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    //LENGTH CONVERSION TESTS

    @Test
    void testConversion_FeetToInches() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_YardsToFeet() {
        Quantity<LengthUnit> q = new Quantity<>(3.0, LengthUnit.YARDS);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        Quantity<LengthUnit> q = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        Quantity<LengthUnit> q = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_InvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    //LENGTH ADDITION TESTS

    @Test
    void testAddition_SameUnit() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = l1.add(l2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> r1 = l1.add(l2);
        Quantity<LengthUnit> r2 = l2.add(l1);

        assertEquals(
                r1.convertTo(LengthUnit.INCHES).getValue(),
                r2.convertTo(LengthUnit.INCHES).getValue(),
                EPSILON
        );
    }

    @Test
    void testAddition_WithZero() {
        Quantity<LengthUnit> l1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(zero);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullOperand() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(null));
    }

    @Test
    void testAddition_TargetUnit_Feet() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit_Inches() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = l1.add(l2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_TargetUnit_Commutative() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> r1 = l1.add(l2, LengthUnit.YARDS);
        Quantity<LengthUnit> r2 = l2.add(l1, LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit_Null() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(l2, null));
    }

    //WEIGHT TESTS

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2.20462, WeightUnit.POUND);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testConversion_KilogramToGram() {
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKilogram() {
        Quantity<WeightUnit> w = new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = w1.add(w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testEquality_NullComparison() {
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(w.equals(null));
    }

    @Test
    void testConversion_ZeroValue() {
        Quantity<WeightUnit> w = new Quantity<>(0.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    //UC10 GENERIC TEST

    @Test
    void testCrossCategoryComparison_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}