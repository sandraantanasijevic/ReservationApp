/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.service;


import Reservation.data.Resurs;
import Reservation.dao.ResursDao;
import Reservation.dao.ResourcesManager;
import Reservation.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResursService {

    private static final ResursService instance = new ResursService();
    private ResursService() {}
    public static ResursService getInstance() { return instance; }

    public void addResurs(Resurs r) throws ReservationException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        con.setAutoCommit(false);

        int id = ResursDao.getInstance().insert(r, con);

        r.setId(id);

        con.commit();
    } catch (SQLException ex) {
        ResourcesManager.rollbackTransactions(con);
        throw new ReservationException("Failed to add resurs " + r, ex);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}

    public Resurs findResurs(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return ResursDao.getInstance().find(id, con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to find resurs with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateResurs(Resurs r) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ResursDao.getInstance().update(r, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to update resurs " + r, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteResurs(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ResursDao.getInstance().delete(id, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to delete resurs with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Resurs> getAllResursi() throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return ResursDao.getInstance().getAll(con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to get all resursi", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
