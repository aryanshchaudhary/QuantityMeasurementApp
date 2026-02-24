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
|  |  ├──Length
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

