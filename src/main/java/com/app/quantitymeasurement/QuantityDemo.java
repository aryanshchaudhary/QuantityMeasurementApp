package com.app.quantitymeasurement;

import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;

public class QuantityDemo {

    public static void runDemo() {

        System.out.println("\n===== Quantity Measurement Demo =====");

        // ================= LENGTH =================
        System.out.println("\n--- LENGTH ---");

        Quantity<LengthUnit> l1 = new Quantity<>(1000.0, LengthUnit.METER);
        Quantity<LengthUnit> l2 = new Quantity<>(1.0, LengthUnit.KILOMETER);

        System.out.println("1000 meter == 1 km → " + l1.equals(l2));

        Quantity<LengthUnit> cm = new Quantity<>(100.0, LengthUnit.CENTIMETER);
        Quantity<LengthUnit> m = new Quantity<>(1.0, LengthUnit.METER);

        System.out.println("100 cm == 1 meter → " + cm.equals(m));

        // Conversion
        System.out.println("\nConversion:");

        Quantity<LengthUnit> meter = new Quantity<>(1.0, LengthUnit.METER);
        System.out.println("1 meter to cm → " + meter.convertTo(LengthUnit.CENTIMETER));

        Quantity<LengthUnit> km = new Quantity<>(1.0, LengthUnit.KILOMETER);
        System.out.println("1 km to meter → " + km.convertTo(LengthUnit.METER));

        // Addition
        System.out.println("\nAddition:");

        Quantity<LengthUnit> addResult =
                new Quantity<>(1.0, LengthUnit.METER)
                        .add(new Quantity<>(100.0, LengthUnit.CENTIMETER));

        System.out.println("1 m + 100 cm → " + addResult);

        // Subtraction
        System.out.println("\nSubtraction:");

        Quantity<LengthUnit> subtractResult =
                new Quantity<>(5.0, LengthUnit.METER)
                        .subtract(new Quantity<>(50.0, LengthUnit.CENTIMETER));

        System.out.println("5 m - 50 cm → " + subtractResult);

        // Division
        System.out.println("\nDivision:");

        double divideResult =
                new Quantity<>(10.0, LengthUnit.METER)
                        .divide(new Quantity<>(2.0, LengthUnit.METER));

        System.out.println("10 m / 2 m → " + divideResult);

        // ================= WEIGHT =================
        System.out.println("\n--- WEIGHT ---");

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("1 kg == 1000 g → " + w1.equals(w2));

        // ================= VOLUME =================
        System.out.println("\n--- VOLUME ---");

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITER);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITER);

        System.out.println("1 L == 1000 mL → " + v1.equals(v2));

        // ================= TEMPERATURE =================
        System.out.println("\n--- TEMPERATURE ---");

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        System.out.println("0 C to F → " +
                t1.convertTo(TemperatureUnit.FAHRENHEIT));
    }
}