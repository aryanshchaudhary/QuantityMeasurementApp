package com.app.quantityservice.service;

import org.springframework.stereotype.Service;

import com.app.quantityservice.dto.QuantityDTO;
import com.app.quantityservice.entity.QuantityMeasurementEntity;
import com.app.quantityservice.repository.IQuantityMeasurementRepository;
import com.app.quantityservice.unit.*;

@Service
@SuppressWarnings("rawtypes")
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repository = repo;
    }

    // ================= DTO → Quantity =================
    @SuppressWarnings("unchecked")
	private Quantity toQuantity(QuantityDTO dto) {

        String unit = dto.getUnit().toUpperCase();

        if (isLengthUnit(unit)) {
            return new Quantity(dto.getValue(), LengthUnit.valueOf(unit));
        }

        if (isWeightUnit(unit)) {
            return new Quantity(dto.getValue(), WeightUnit.valueOf(unit));
        }

        if (isVolumeUnit(unit)) {
            return new Quantity(dto.getValue(), VolumeUnit.valueOf(unit));
        }

        if (isTemperatureUnit(unit)) {
            return new Quantity(dto.getValue(), TemperatureUnit.valueOf(unit));
        }

        throw new IllegalArgumentException("Invalid unit: " + unit);
    }

    // ================= UNIT CHECK HELPERS =================
    private boolean isLengthUnit(String unit) {
        try { LengthUnit.valueOf(unit); return true; }
        catch (Exception e) { return false; }
    }

    private boolean isWeightUnit(String unit) {
        try { WeightUnit.valueOf(unit); return true; }
        catch (Exception e) { return false; }
    }

    private boolean isVolumeUnit(String unit) {
        try { VolumeUnit.valueOf(unit); return true; }
        catch (Exception e) { return false; }
    }

    private boolean isTemperatureUnit(String unit) {
        try { TemperatureUnit.valueOf(unit); return true; }
        catch (Exception e) { return false; }
    }

    // ================= COMPARE =================
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        boolean result = quantity1.equals(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }

    // ================= CONVERT =================
    @SuppressWarnings("unchecked")
	@Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity quantity = toQuantity(q);

        String unit = targetUnit.toUpperCase();
        Quantity result;

        if (isLengthUnit(unit)) {
            result = quantity.convertTo(LengthUnit.valueOf(unit));
        } else if (isWeightUnit(unit)) {
            result = quantity.convertTo(WeightUnit.valueOf(unit));
        } else if (isVolumeUnit(unit)) {
            result = quantity.convertTo(VolumeUnit.valueOf(unit));
        } else if (isTemperatureUnit(unit)) {
            result = quantity.convertTo(TemperatureUnit.valueOf(unit));
        } else {
            throw new IllegalArgumentException("Invalid target unit: " + unit);
        }

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                q.toString(),
                targetUnit,
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName()
        );
    }

    // ================= ADD =================
    @SuppressWarnings("unchecked")
	@Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (isTemperatureUnit(q1.getUnit())) {
            throw new IllegalArgumentException("Addition not supported for Temperature");
        }

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        Quantity result = quantity1.add(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.toString(),
                q2.toString(),
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName()
        );
    }

    // ================= SUBTRACT =================
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        if (isTemperatureUnit(q1.getUnit())) {
            throw new IllegalArgumentException("Subtraction not supported for Temperature");
        }

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        @SuppressWarnings("unchecked")
		Quantity result = quantity1.subtract(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.toString(),
                q2.toString(),
                result.toString()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName()
        );
    }

    // ================= DIVIDE =================
    @SuppressWarnings("unchecked")
	@Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if (isTemperatureUnit(q1.getUnit())) {
            throw new IllegalArgumentException("Division not supported for Temperature");
        }

        Quantity quantity1 = toQuantity(q1);
        Quantity quantity2 = toQuantity(q2);

        double result = quantity1.divide(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }
}