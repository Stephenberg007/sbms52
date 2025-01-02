package com.jtc.generator;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "OD";
        String suffix = "";

        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection();
             Statement stmt = connection.createStatement()) {

            // Fetch and increment the sequence value
            String selectQuery = "SELECT seq_value FROM order_id_sequence WHERE seq_name = 'order_id_seq'";
            ResultSet rs = stmt.executeQuery(selectQuery);

            if (rs.next()) {
                int currentValue = rs.getInt("seq_value");
                suffix = String.valueOf(currentValue);

                // Increment the sequence value
                String updateQuery = "UPDATE order_id_sequence SET seq_value = seq_value + 1 WHERE seq_name = 'order_id_seq'";
                stmt.executeUpdate(updateQuery);
            } else {
                throw new RuntimeException("Sequence not found for 'order_id_seq'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prefix + suffix;
    }
}
