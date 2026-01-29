/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.service;


import Reservation.data.Rezervacija;
import Reservation.data.RezervacionaSerija;
import Reservation.dao.RezervacijaDao;
import Reservation.dao.RezervacionaSerijaDao;
import Reservation.dao.ResourcesManager;
import Reservation.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RezervacijaService {

    private static final RezervacijaService instance = new RezervacijaService();
    private RezervacijaService() {}
    public static RezervacijaService getInstance() { return instance; }

    public void addRezervacija(Rezervacija r) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacijaDao.getInstance().insert(r, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to add rezervacija " + r, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void addRezervacijaWithSerija(Rezervacija r, RezervacionaSerija s)
        throws ReservationException {

        Connection con = null;

        try {

            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            int serijaId = RezervacionaSerijaDao.getInstance().insert(s, con);

            s.setId(serijaId);

            r.setSerijaId(serijaId);

            RezervacijaDao.getInstance().insert(r, con);

            con.commit();

            } catch (Exception ex) {

                ResourcesManager.rollbackTransactions(con);

                throw new ReservationException("Greška pri kreiranju rezervacije sa serijom!", ex);

            } finally {

                ResourcesManager.closeConnection(con);
            }
    }

    public Rezervacija findRezervacija(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return RezervacijaDao.getInstance().find(id, con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to find rezervacija with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void updateRezervacija(Rezervacija r) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacijaDao.getInstance().update(r, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to update rezervacija " + r, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public void deleteRezervacija(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            RezervacijaDao.getInstance().delete(id, con);
            con.commit();
        } catch(SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new ReservationException("Failed to delete rezervacija with id " + id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }

    public List<Rezervacija> getAllRezervacije() throws ReservationException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            return RezervacijaDao.getInstance().getAll(con);
        } catch(SQLException ex) {
            throw new ReservationException("Failed to get all rezervacije", ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Rezervacija> getRezervacijeZaKorisnika(int korisnikId) throws ReservationException {
    Connection con = null;
    try {
        con = ResourcesManager.getConnection();
        return RezervacijaDao.getInstance().findByKorisnik(korisnikId, con);
    } catch (SQLException ex) {
        throw new ReservationException("Ne mogu da dohvatim rezervacije za korisnika " + korisnikId, ex);
    } finally {
        ResourcesManager.closeConnection(con);
    }
}
}