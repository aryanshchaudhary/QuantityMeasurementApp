# QuantityMeasurementApp
---

## 📂 Repository Structure
```
QuantityMeasurementApp/
|
├──.mvn
├──.settings
├──src
|  |
|  ├──main
|  |  |
|  |  ├──QuantityMeasurementApp
|  |  ├──IMeasurable
|  |  ├──LengthUnit
|  |  ├──Quantity
|  |  ├──VolumeUnit
|  |  ├──WeightUnit
|  |
|  ├──test
|  |  |
|  |  ├──QuantityMeasurementAppTest
|  |
├──target
├──pom

```

---
**UC1: Feet Measurement Equality**
  - The QuantityMeasurementApp class is responsible for checking the equality of two numerical values measured in feet within the Quantity Measurement Application. It ensures accurate comparisons and handles various edge cases.
  - https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/756d38fc470f273efb45df09a4d63bc23fd5dd54
---
**UC2: Inch Measurement Equality**
  - This Use Case extends UC1 to accommodate the Equality Check for Inches along with Feet. This use case is in no way trying to compare two entities, Feet and Inches. They are still treated separately. Please ensure like UC1 the test cases ensure complete test coverage to accurately compare and handle various edge cases.
  - https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/8a80a920e772a7e139d0bb0eef75725506deee5a
--- 
**UC3: GenericQuantityClassForDRYPrinciple**
  - UC3 is designed to overcome the Disadvantage of using Feet and Inches which starts violating the DRY principle, where both Feet and Inches classes contain nearly identical code, having the same constructor pattern, Identical equals() method implementation.
  - This Use Case refactors the existing Feet and Inches classes into a single generic Quantity Length class that eliminates code duplication while maintaining all functionality from UC1 and UC2. The Quantity Length class represents any measurement with a value and unit type, applying the DRY (Don't Repeat Yourself) principle. This reduces maintenance burden and makes the codebase more scalable for adding new units in the future.
  - https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/f48bd515ac4859bda0c1e74a857c11c61feef11b
---
**UC4: Extended Unit Support**
  - UC4 extends UC3 by introducing Yards and Centimeters as additional length units to the QuantityLength class. This use case demonstrates how the generic Quantity class design scales effortlessly to accommodate new units without code duplication. Yards will be added to the LengthUnit enum with the appropriate conversion factor (1 yard = 3 feet) and (1cm = 0.393701in), and all equality comparisons will work seamlessly across feet, inches, yards, and cms.
  - https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/3d5882c5a01070299778a9120849d40040690057
---
**UC5: Unit-to-Unit Conversion**
  - UC5 extends UC4 by providing explicit conversion operations between length units (e.g., feet → inches, yards → inches, centimeters → feet). Instead of only comparing equality, the Quantity Length API exposes a conversion method that returns a numeric value converted from a source unit to a target unit using the centralized conversion factors.
  - https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/c5d11086059a39dbfbc9d09410ab1eee96315916
--- 
**UC6: Addition of Two Length Units**
- UC6 extends UC5 by introducing addition operations between length measurements. This use case enables the Quantity Length API to add two lengths of potentially different units (but same category—length) and return the result in the unit of the first operand. Essentially adding another length to the current length. For example, adding 1 foot and 12 inches should yield 2 feet (based on the unit of the first operand).
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/b02a52cbae4017c4a0454060fbcb32194108c618
---
**UC7: Addition with Target Unit Specification**
- UC7 extends UC6 by providing flexibility in specifying the unit for the addition result. Instead of defaulting to the unit of the first operand, this use case allows the caller to explicitly specify any supported unit as the target unit for the result. This provides greater flexibility in use cases where the result must be expressed in a specific unit regardless of the operands' units. For example, adding 1 foot and 12 inches with a target unit of yards should yield approximately 0.667 yards.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/3ac1d5dfe6f78cffcc4a44388d8d19d59800184b
---
**UC8: Refactoring Unit Enum to Standalone with Conversion Responsibility**
- UC8 refactors the design from UC1–UC7 to overcome the disadvantage of embedding the LengthUnit enum within the QuantityLength class. This design flaw creates circular dependencies when scaling to multiple measurement categories (length, weight, volume, etc.) and violates the Single Responsibility Principle by not centralizing unit-related conversion logic.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/136f2b8effabc1df4e4f84471fc58853e756f347
---
**UC9: Weight Measurement Equality, Conversion, and Addition**
- UC9 extends the Quantity Measurement Application to support weight measurements alongside length measurements. This use case introduces a new measurement category—weight—that operates independently from length. Similar to how length measurements (feet, inches, yards, centimeters) are compared for equality, converted between units, and added together, weight measurements in different units (kilograms, grams, pounds) will support the same operations.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/a975d3c4ace042820a68ba2844c32c794b7776b1
---
**UC10: Generic Quantity Class with Unit Interface for Multi-Category Support**
- UC10 addresses the architectural and design disadvantages introduced by UC9 by refactoring the design into a single, generic Quantity<U> class that works with any measurement category through a common IMeasurable interface. This use case eliminates code duplication across parallel QuantityLength and QuantityWeight classes, consolidates unit enum patterns, and simplifies the QuantityMeasurementApp class to adhere to the Single Responsibility Principle.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/884a0e224e134d2be1966ef0570d77f2af51df67
--- 
**UC11: Volume Measurement Equality, Conversion, and Addition (Litre, Millilitre, Gallon)**
- UC11 extends the Quantity Measurement Application to support volume measurements alongside length and weight measurements. This use case introduces a new measurement category—volume—that operates independently from length and weight through the generic Quantity<U> class and IMeasurable interface established in UC10.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/dafede8b7f4203b2fbae16d23b07bb749435a902
--- 
**UC12: Subtraction and Division Operations on Quantity Measurements**
- UC12 extends the Quantity Measurement Application by introducing two new arithmetic operations—subtraction and division—to the generic Quantity<U> class. Building on the foundation of equality comparison, unit conversion, and addition from UC1–UC11, this use case enables more comprehensive arithmetic manipulation of measurements.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/ace8f3fc62721e6f1d7251669379ce9077448655
---
**UC13: Centralized Arithmetic Logic to Enforce DRY in Quantity Operations**
- UC13 refactors the arithmetic operations (addition, subtraction, division) implemented in UC12 to eliminate code duplication and enforce the DRY (Don't Repeat Yourself) principle. Instead of repeating unit conversion, base-unit normalization, and validation logic across multiple arithmetic methods, this use case introduces a centralized private helper method that encapsulates all common arithmetic logic.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/40226307ec59467acd41513f778edd78c7740a27
---
**UC14: Temperature Measurement with Selective Arithmetic Support and IMeasurable Refactoring**
- UC14 extends the Quantity Measurement Application to support temperature measurements alongside length, weight, and volume. Unlike these three categories, which support full arithmetic operations (addition, subtraction, division), temperature presents a unique challenge: temperature differences can be added or subtracted, but individual temperature values cannot be meaningfully multiplied or divided in most practical contexts.
- https://github.com/aryanshchaudhary/QuantityMeasurementApp/commit/2a7dbb0d1775a1aead83f1d92e9f84147f9c6321
--- 
