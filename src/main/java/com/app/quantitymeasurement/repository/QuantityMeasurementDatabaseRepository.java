package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private ConnectionPool pool = new ConnectionPool();

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurement " +
                "(operation, operand1, operand2, result, error) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;

        try {

            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getOperation());
            stmt.setString(2, entity.getOperand1());
            stmt.setString(3, entity.getOperand2());
            stmt.setString(4, entity.getResult());
            stmt.setString(5, entity.getError());

            stmt.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Error saving measurement", e);

        } finally {

            if (conn != null)
                pool.releaseConnection(conn);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM quantity_measurement";

        Connection conn = null;

        try {

            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String operation = rs.getString("operation");
                String op1 = rs.getString("operand1");
                String op2 = rs.getString("operand2");
                String result = rs.getString("result");

                QuantityMeasurementEntity entity =
                        new QuantityMeasurementEntity(operation, op1, op2, result);

                list.add(entity);
            }

        } catch (Exception e) {

            throw new DatabaseException("Error retrieving measurements", e);

        } finally {

            if (conn != null)
                pool.releaseConnection(conn);
        }

        return list;
    }

    @Override
    public void deleteAll() {

        String sql = "DELETE FROM quantity_measurement";

        Connection conn = null;

        try {

            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException("Error deleting measurements", e);

        } finally {

            if (conn != null)
                pool.releaseConnection(conn);
        }
    }
}