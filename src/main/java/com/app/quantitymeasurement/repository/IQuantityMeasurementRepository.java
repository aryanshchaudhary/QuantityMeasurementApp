package com.app.quantitymeasurement.repository;

import java.util.List;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();
    
    void deleteAll();
}