/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

import Reservation.data.RezervacionaSerija;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervacionaSerijaDao {
    private static final RezervacionaSerijaDao instance = new RezervacionaSerijaDao();
    private RezervacionaSerijaDao() {}
    public static RezervacionaSerijaDao getInstance() { return instance; }

    public RezervacionaSerija find(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM rezervaciona_serija WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new RezervacionaSerija(rs.getInt("id"), rs.getString("tip"), rs.getDate("do_datum"));
                }
            }
        }
        return null;
    }

    public int insert(RezervacionaSerija s, Connection con) throws SQLException {
        String sql = "INSERT INTO rezervaciona_serija(tip, do_datum) VALUES(?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getTip());
            ps.setDate(2, s.getDoDatum());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()){
                    s.setId(rs.getInt(1));  // ✅ Postavi ID u objekat
                    return rs.getInt(1);
}    }
            }
        

        return -1;
    }

    public void update(RezervacionaSerija s, Connection con) throws SQLException {
        String sql = "UPDATE rezervaciona_serija SET tip=?, do_datum=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getTip());
            ps.setDate(2, s.getDoDatum());
            ps.setInt(3, s.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM rezervaciona_serija WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<RezervacionaSerija> getAll(Connection con) throws SQLException {
        List<RezervacionaSerija> lista = new ArrayList<>();
        String sql = "SELECT * FROM rezervaciona_serija";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                lista.add(new RezervacionaSerija(rs.getInt("id"), rs.getString("tip"), rs.getDate("do_datum")));
            }
        }
        return lista;
    }
}
