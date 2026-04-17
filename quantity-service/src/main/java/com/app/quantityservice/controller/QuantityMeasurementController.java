package com.app.quantityservice.controller;

import org.springframework.web.bind.annotation.*;

import com.app.quantityservice.dto.QuantityDTO;
import com.app.quantityservice.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityDTO[] quantities) {
        return service.compare(quantities[0], quantities[1]);
    }

    @PostMapping("/add")
    public QuantityDTO add(@RequestBody QuantityDTO[] quantities) {
        return service.add(quantities[0], quantities[1]);
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody QuantityDTO[] quantities) {
        return service.subtract(quantities[0], quantities[1]);
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityDTO[] quantities) {
        return service.divide(quantities[0], quantities[1]);
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit) {
        return service.convert(quantity, targetUnit);
    }
}