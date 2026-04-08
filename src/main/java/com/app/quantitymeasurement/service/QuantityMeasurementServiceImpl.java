package com.app.quantitymeasurement.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.*;
import com.app.quantitymeasurement.user.User;
import com.app.quantitymeasurement.user.UserRepository;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;
    private final UserRepository userRepository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo,
                                          UserRepository userRepository) {
        this.repository = repo;
        this.userRepository = userRepository;
    }

    // ================= DTO → Quantity =================
    private Quantity<? extends IMeasurable> toQuantity(QuantityDTO dto) {

        String unit = dto.getUnit().toUpperCase();

        if (isLengthUnit(unit)) {
            return new Quantity<>(dto.getValue(), LengthUnit.valueOf(unit));
        }

        if (isWeightUnit(unit)) {
            return new Quantity<>(dto.getValue(), WeightUnit.valueOf(unit));
        }

        if (isVolumeUnit(unit)) {
            return new Quantity<>(dto.getValue(), VolumeUnit.valueOf(unit));
        }

        if (isTemperatureUnit(unit)) {
            return new Quantity<>(dto.getValue(), TemperatureUnit.valueOf(unit));
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

    // ================= GET CURRENT USER =================
    private User getCurrentUser() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElse(null);
    }

    // ================= COMPARE =================
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<? extends IMeasurable> quantity1 = toQuantity(q1);
        Quantity<? extends IMeasurable> quantity2 = toQuantity(q2);

        boolean result = ((Quantity) quantity1).equals(quantity2);

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                getCurrentUser()
        ));

        return result;
    }

    // ================= CONVERT =================
    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity<? extends IMeasurable> quantity = toQuantity(q);

        String unit = targetUnit.toUpperCase();
        Quantity result;

        if (isLengthUnit(unit)) {
            result = ((Quantity) quantity).convertTo(LengthUnit.valueOf(unit));
        } else if (isWeightUnit(unit)) {
            result = ((Quantity) quantity).convertTo(WeightUnit.valueOf(unit));
        } else if (isVolumeUnit(unit)) {
            result = ((Quantity) quantity).convertTo(VolumeUnit.valueOf(unit));
        } else if (isTemperatureUnit(unit)) {
            result = ((Quantity) quantity).convertTo(TemperatureUnit.valueOf(unit));
        } else {
            throw new IllegalArgumentException("Invalid target unit: " + unit);
        }

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                q.toString(),
                targetUnit,
                result.toString(),
                getCurrentUser()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName()
        );
    }

    // ================= ADD =================
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (isTemperatureUnit(q1.getUnit())) {
            throw new IllegalArgumentException("Addition not supported for Temperature");
        }

        Quantity<? extends IMeasurable> quantity1 = toQuantity(q1);
        Quantity<? extends IMeasurable> quantity2 = toQuantity(q2);

        Quantity result = ((Quantity) quantity1).add((Quantity) quantity2);

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.toString(),
                q2.toString(),
                result.toString(),
                getCurrentUser()
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

        Quantity<? extends IMeasurable> quantity1 = toQuantity(q1);
        Quantity<? extends IMeasurable> quantity2 = toQuantity(q2);

        Quantity result = ((Quantity) quantity1).subtract((Quantity) quantity2);

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.toString(),
                q2.toString(),
                result.toString(),
                getCurrentUser()
        ));

        return new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName()
        );
    }

    // ================= DIVIDE =================
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        if (isTemperatureUnit(q1.getUnit())) {
            throw new IllegalArgumentException("Division not supported for Temperature");
        }

        Quantity<? extends IMeasurable> quantity1 = toQuantity(q1);
        Quantity<? extends IMeasurable> quantity2 = toQuantity(q2);

        double result = ((Quantity) quantity1).divide((Quantity) quantity2);

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                getCurrentUser()
        ));

        return result;
    }
}

