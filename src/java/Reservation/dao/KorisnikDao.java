/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.dao;

/**
 *
 * @author Sandra
 */
import Reservation.data.Korisnik;
import java.sql.*;

public class KorisnikDao {

    private static final KorisnikDao instance = new KorisnikDao();

    private KorisnikDao() {}

    public static KorisnikDao getInstance() {
        return instance;
    }

    public Korisnik find(String username, Connection con) throws SQLException {
        String sql = "SELECT * FROM korisnik WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Korisnik(rs.getInt("id"), rs.getString("username"),
                            rs.getString("password"), rs.getString("ime"));
                }
            }
        }
        return null;
    }

    public int insert(Korisnik k, Connection con) throws SQLException {
        String sql = "INSERT INTO korisnik(username, password, ime) VALUES(?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, k.getUsername());
            ps.setString(2, k.getPassword());
            ps.setString(3, k.getIme());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void update(Korisnik k, Connection con) throws SQLException {
        String sql = "UPDATE korisnik SET password=?, ime=? WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getPassword());
            ps.setString(2, k.getIme());
            ps.setString(3, k.getUsername());
            ps.executeUpdate();
        }
    }

    public void delete(Korisnik k, Connection con) throws SQLException {
        String sql = "DELETE FROM korisnik WHERE username=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getUsername());
            ps.executeUpdate();
        }
    }
}