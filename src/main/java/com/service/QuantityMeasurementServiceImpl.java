package com.service;

import com.dto.QuantityDTO;
import com.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repository = repo;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return q1.getValue() == q2.getValue();
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {
        return new QuantityDTO(q.getValue(), targetUnit);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return new QuantityDTO(q1.getValue() + q2.getValue(), q1.getUnit());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return new QuantityDTO(q1.getValue() - q2.getValue(), q1.getUnit());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return q1.getValue() / q2.getValue();
    }
}