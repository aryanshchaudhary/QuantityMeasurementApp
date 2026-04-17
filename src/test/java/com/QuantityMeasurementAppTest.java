package com;

import org.junit.jupiter.api.Test;

import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ================= LENGTH TESTS =================

    @Test
    void testLength_MeterToCentimeter_Equivalent() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.METER);
        Quantity<LengthUnit> l2 = new Quantity<>(100.0, LengthUnit.CENTIMETER);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testLength_KilometerToMeter_Equivalent() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.KILOMETER);
        Quantity<LengthUnit> l2 = new Quantity<>(1000.0, LengthUnit.METER);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testConversion_MeterToCentimeter() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.METER);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.CENTIMETER);

        assertEquals(100.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_KilometerToMeter() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.KILOMETER);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.METER);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_MeterPlusCentimeter() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.METER);
        Quantity<LengthUnit> l2 = new Quantity<>(100.0, LengthUnit.CENTIMETER);

        Quantity<LengthUnit> result = l1.add(l2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_MeterMinusCentimeter() {
        Quantity<LengthUnit> l1 = new Quantity<>(5.0, LengthUnit.METER);
        Quantity<LengthUnit> l2 = new Quantity<>(100.0, LengthUnit.CENTIMETER);

        Quantity<LengthUnit> result = l1.subtract(l2);

        assertEquals(4.0, result.getValue(), EPSILON);
    }

    @Test
    void testDivision_Meter() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.METER);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.METER);

        double result = q1.divide(q2);

        assertEquals(5.0, result, EPSILON);
    }

    // ================= WEIGHT =================

    @Test
    void testWeight_KgToGram() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    // ================= VOLUME =================

    @Test
    void testVolume_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITER);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITER);

        assertTrue(v1.equals(v2));
    }

    // ================= TEMPERATURE =================

    @Test
    void testTemperature_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }
}