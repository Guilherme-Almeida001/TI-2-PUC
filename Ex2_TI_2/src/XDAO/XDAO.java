package XDAO;

import conection.conection;
import X.X;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class XDAO {

    public void insert(X x) {
        String sql = "INSERT INTO x (name, description, value) VALUES (?, ?, ?)";

        try (Connection conn = conection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, x.getName());
            stmt.setString(2, x.getDescription());
            stmt.setDouble(3, x.getValue());

            stmt.executeUpdate();
            System.out.println("Record inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }

    public List<X> list() {
        List<X> list = new ArrayList<>();
        String sql = "SELECT * FROM x ORDER BY id";

        try (Connection conn = conection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                X x = new X(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("value")
                );
                list.add(x);
            }

        } catch (SQLException e) {
            System.out.println("Error listing records: " + e.getMessage());
        }

        return list;
    }

    public void update(X x) {
        String sql = "UPDATE x SET name = ?, description = ?, value = ? WHERE id = ?";

        try (Connection conn = conection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, x.getName());
            stmt.setString(2, x.getDescription());
            stmt.setDouble(3, x.getValue());
            stmt.setInt(4, x.getId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("Record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM x WHERE id = ?";

        try (Connection conn = conection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("Record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}