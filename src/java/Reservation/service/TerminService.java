/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.service;


import Reservation.data.Termin;
import Reservation.dao.TerminDao;
import Reservation.service.TerminService;
import Reservation.dao.ResourcesManager;
import Reservation.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date; 

public class TerminService {

    private static final TerminService instance = new TerminService();
    private TerminService() {}
    public static TerminService getInstance() { return instance; }

    public void addTermin(Termin t) throws ReservationException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        con.setAutoCommit(false);

        int id = TerminDao.getInstance().insert(t, con);
        t.setId(id);

        con.commit();
    } catch (SQLException ex) {
        ResourcesManager.rollbackTransactions(con);
        throw new ReservationException("Failed to add termin " + t, ex);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}


    public Termin findTermin(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return TerminDao.getInstance().find(id, con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to find termin with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    

    public void updateTermin(Termin t) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            TerminDao.getInstance().update(t, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to update termin " + t, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteTermin(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            TerminDao.getInstance().delete(id, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to delete termin with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Termin> getAllTermini() throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return TerminDao.getInstance().getAll(con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to get all termini", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Termin> getSlobodniTermini(Date datum, int resursId) throws ReservationException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        return TerminDao.getInstance().findSlobodniTermini(datum, resursId, con);
    } catch (SQLException ex) {
        throw new ReservationException("Greška pri dohvatanju slobodnih termina", ex);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}
}