/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.service;


import Reservation.data.RezervacionaSerija;
import Reservation.dao.RezervacionaSerijaDao;
import Reservation.dao.ResourcesManager;
import Reservation.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RezervacionaSerijaService {

    private static final RezervacionaSerijaService instance = new RezervacionaSerijaService();
    private RezervacionaSerijaService() {}
    public static RezervacionaSerijaService getInstance() { return instance; }

    public void addSerija(RezervacionaSerija s) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacionaSerijaDao.getInstance().insert(s, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to add serija " + s, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public RezervacionaSerija findSerija(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return RezervacionaSerijaDao.getInstance().find(id, con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to find serija with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateSerija(RezervacionaSerija s) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacionaSerijaDao.getInstance().update(s, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to update serija " + s, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteSerija(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacionaSerijaDao.getInstance().delete(id, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to delete serija with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<RezervacionaSerija> getAllSerije() throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return RezervacionaSerijaDao.getInstance().getAll(con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to get all serije", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}