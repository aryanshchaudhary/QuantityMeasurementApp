package com.app.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String error;

    public QuantityMeasurementEntity(String operation, String op1, String op2, String result) {
        this.operation = operation;
        this.operand1 = op1;
        this.operand2 = op2;
        this.result = result;
    }

    public QuantityMeasurementEntity(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
    
    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }
    
    @Override
    public String toString() {
        if (error != null) {
            return "Error: " + error;
        }

        return "Operation=" + operation +
               ", Operand1=" + operand1 +
               ", Operand2=" + operand2 +
               ", Result=" + result;
    }
}