/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourcesManager {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost/rezervacija_db?useSSL=false&allowPublicKeyRetrieval=true",
            "root", "" 
        );
    }

    public static void closeResources(ResultSet rs, PreparedStatement ps) {
        try { if (rs != null) rs.close(); } catch(SQLException ex){ ex.printStackTrace(); }
        try { if (ps != null) ps.close(); } catch(SQLException ex){ ex.printStackTrace(); }
    }

    public static void closeConnection(Connection con) {
        try { if (con != null) con.close(); } catch(SQLException ex){ ex.printStackTrace(); }
    }

    public static void rollbackTransactions(Connection con) {
        try { if (con != null) con.rollback(); } catch(SQLException ex){ ex.printStackTrace(); }
    }
}