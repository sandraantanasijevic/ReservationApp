/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

import Reservation.data.Resurs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResursDao {
    private static final ResursDao instance = new ResursDao();
    private ResursDao() {}
    public static ResursDao getInstance() { return instance; }

    public Resurs find(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM resurs WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new Resurs(rs.getInt("id"), rs.getString("naziv"), rs.getString("tip"),
                            rs.getTime("radno_od"), rs.getTime("radno_do"));
                }
            }
        }
        return null;
    }

    public int insert(Resurs r, Connection con) throws SQLException {
        String sql = "INSERT INTO resurs(naziv, tip, radno_od, radno_do) VALUES(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getNaziv());
            ps.setString(2, r.getTip());
            ps.setTime(3, r.getRadnoOd());
            ps.setTime(4, r.getRadnoDo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void update(Resurs r, Connection con) throws SQLException {
        String sql = "UPDATE resurs SET naziv=?, tip=?, radno_od=?, radno_do=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getNaziv());
            ps.setString(2, r.getTip());
            ps.setTime(3, r.getRadnoOd());
            ps.setTime(4, r.getRadnoDo());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM resurs WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Resurs> getAll(Connection con) throws SQLException {
        List<Resurs> lista = new ArrayList<>();
        String sql = "SELECT * FROM resurs";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                lista.add(new Resurs(rs.getInt("id"), rs.getString("naziv"), rs.getString("tip"),
                        rs.getTime("radno_od"), rs.getTime("radno_do")));
            }
        }
        return lista;
    }
}