/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.service;


import Reservation.data.Korisnik;
import Reservation.dao.KorisnikDao;
import Reservation.dao.ResourcesManager;
import Reservation.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;

public class KorisnikService {

    private static final KorisnikService instance = new KorisnikService();
    private KorisnikService() {}
    public static KorisnikService getInstance() { return instance; }

    public void addNewKorisnik(Korisnik k) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            KorisnikDao.getInstance().insert(k, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to add Korisnik " + k, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public Korisnik findKorisnik(String username) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return KorisnikDao.getInstance().find(username, con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to find Korisnik " + username, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateKorisnik(Korisnik k) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            KorisnikDao.getInstance().update(k, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to update Korisnik " + k, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteKorisnik(Korisnik k) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            KorisnikDao.getInstance().delete(k, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to delete Korisnik " + k, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}