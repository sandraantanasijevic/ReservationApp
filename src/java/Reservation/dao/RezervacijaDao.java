/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

import Reservation.data.Rezervacija;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaDao {
    private static final RezervacijaDao instance = new RezervacijaDao();
    private RezervacijaDao() {}
    public static RezervacijaDao getInstance() { return instance; }

    public Rezervacija find(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM rezervacija WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new Rezervacija(rs.getInt("id"), rs.getInt("korisnik_id"), rs.getInt("termin_id"),
                            rs.getString("status"), rs.getTimestamp("datum_rezervacije"), rs.getInt("serija_id"));
                }
            }
        }
        return null;
    }
    
    public List<Rezervacija> findByKorisnik(int korisnikId, Connection con) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Rezervacija> lista = new ArrayList<>();

    try {
        ps = con.prepareStatement(
            "SELECT * FROM rezervacija WHERE korisnik_id = ?"
        );
        ps.setInt(1, korisnikId);
        rs = ps.executeQuery();

        while (rs.next()) {
        Rezervacija r = new Rezervacija(
        rs.getInt("id"),
        rs.getInt("korisnik_id"),
        rs.getInt("termin_id"),
        rs.getString("status"),
        rs.getTimestamp("datum_rezervacije"),
        rs.getInt("serija_id")
    );
    lista.add(r);
}
    } finally {
        ResourcesManager.closeResources(rs, ps);
    }

    return lista;
}

    public int insert(Rezervacija r, Connection con) throws SQLException {
        String sql = "INSERT INTO rezervacija(korisnik_id, termin_id, status, serija_id) VALUES(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getKorisnikId());
            ps.setInt(2, r.getTerminId());
            ps.setString(3, r.getStatus());
            ps.setInt(4, r.getSerijaId());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void update(Rezervacija r, Connection con) throws SQLException {
        String sql = "UPDATE rezervacija SET korisnik_id=?, termin_id=?, status=?, serija_id=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getKorisnikId());
            ps.setInt(2, r.getTerminId());
            ps.setString(3, r.getStatus());
            ps.setInt(4, r.getSerijaId());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM rezervacija WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Rezervacija> getAll(Connection con) throws SQLException {
        List<Rezervacija> lista = new ArrayList<>();
        String sql = "SELECT * FROM rezervacija";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()) {
                lista.add(new Rezervacija(rs.getInt("id"), rs.getInt("korisnik_id"), rs.getInt("termin_id"),
                        rs.getString("status"), rs.getTimestamp("datum_rezervacije"), rs.getInt("serija_id")));
            }
        }
        return lista;
    }
}