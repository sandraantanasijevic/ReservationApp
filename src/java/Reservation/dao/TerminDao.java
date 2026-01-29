/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

import Reservation.data.Termin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerminDao {
    private static final TerminDao instance = new TerminDao();
    private TerminDao() {}
    public static TerminDao getInstance() { return instance; }

    public Termin find(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM termin WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new Termin(rs.getInt("id"), rs.getDate("datum"), rs.getTime("vreme_od"),
                            rs.getTime("vreme_do"), rs.getInt("resurs_id"));
                }
            }
        }
        return null;
    }
    
    public List<Termin> findSlobodniTermini(Date datum, int resursId, Connection con) throws SQLException {
    List<Termin> termini = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String sql =
            "SELECT t.* FROM termin t " +
            "LEFT JOIN rezervacija r ON t.id = r.termin_id AND r.status = 'AKTIVNA' " +
            "WHERE t.datum = ? AND t.resurs_id = ? AND r.id IS NULL";

        ps = con.prepareStatement(sql);
        ps.setDate(1, datum);
        ps.setInt(2, resursId);

        rs = ps.executeQuery();

        while (rs.next()) {
            Termin t = new Termin(
                rs.getInt("id"),
                rs.getDate("datum"),
                rs.getTime("vreme_od"),
                rs.getTime("vreme_do"),
                rs.getInt("resurs_id")
            );
            termini.add(t);
        }
    } finally {
        ResourcesManager.closeResources(rs, ps);
    }

    return termini;
}

    public int insert(Termin t, Connection con) throws SQLException {
        String sql = "INSERT INTO termin(datum, vreme_od, vreme_do, resurs_id) VALUES(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, t.getDatum());
            ps.setTime(2, t.getVremeOd());
            ps.setTime(3, t.getVremeDo());
            ps.setInt(4, t.getResursId());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void update(Termin t, Connection con) throws SQLException {
        String sql = "UPDATE termin SET datum=?, vreme_od=?, vreme_do=?, resurs_id=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, t.getDatum());
            ps.setTime(2, t.getVremeOd());
            ps.setTime(3, t.getVremeDo());
            ps.setInt(4, t.getResursId());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM termin WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Termin> getAll(Connection con) throws SQLException {
        List<Termin> lista = new ArrayList<>();
        String sql = "SELECT * FROM termin";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                lista.add(new Termin(rs.getInt("id"), rs.getDate("datum"), rs.getTime("vreme_od"),
                        rs.getTime("vreme_do"), rs.getInt("resurs_id")));
            }
        }
        return lista;
    }
}