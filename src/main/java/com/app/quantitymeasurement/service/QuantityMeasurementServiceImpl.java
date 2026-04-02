package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repository = repo;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = q1.getValue() == q2.getValue();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "COMPARE",
                        q1.toString(),
                        q2.toString(),
                        String.valueOf(result)
                );

        repository.save(entity);   //  UC16 DATABASE SAVE

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        QuantityDTO result = new QuantityDTO(q.getValue(), targetUnit);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "CONVERT",
                        q.toString(),
                        targetUnit,
                        result.toString()
                );

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        QuantityDTO result =
                new QuantityDTO(q1.getValue() + q2.getValue(), q1.getUnit());

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "ADD",
                        q1.toString(),
                        q2.toString(),
                        result.toString()
                );

        repository.save(entity);

        return result;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        QuantityDTO result =
                new QuantityDTO(q1.getValue() - q2.getValue(), q1.getUnit());

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "SUBTRACT",
                        q1.toString(),
                        q2.toString(),
                        result.toString()
                );

        repository.save(entity);

        return result;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        double result = q1.getValue() / q2.getValue();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        "DIVIDE",
                        q1.toString(),
                        q2.toString(),
                        String.valueOf(result)
                );

        repository.save(entity);

        return result;
    }
}