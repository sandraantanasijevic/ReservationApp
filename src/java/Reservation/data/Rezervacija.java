/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.data;

/**
 *
 * @author Sandra
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class Rezervacija implements Serializable {
    private int id;
    private int korisnikId; // ili Korisnik objekat
    private int terminId;   // ili Termin objekat
    private String status;
    private Timestamp datumRezervacije;
    private int serijaId; // može i objekat RezervacionaSerija

    public Rezervacija() {}

    public Rezervacija(int id, int korisnikId, int terminId, String status, Timestamp datumRezervacije, int serijaId) {
        this.id = id;
        this.korisnikId = korisnikId;
        this.terminId = terminId;
        this.status = status;
        this.datumRezervacije = datumRezervacije;
        this.serijaId = serijaId;
    }

    public Rezervacija(int korisnikId, int terminId, String status, int serijaId) {
        this.korisnikId = korisnikId;
        this.terminId = terminId;
        this.status = status;
        this.serijaId = serijaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getKorisnikId() { return korisnikId; }
    public void setKorisnikId(int korisnikId) { this.korisnikId = korisnikId; }
    public int getTerminId() { return terminId; }
    public void setTerminId(int terminId) { this.terminId = terminId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getDatumRezervacije() { return datumRezervacije; }
    public void setDatumRezervacije(Timestamp datumRezervacije) { this.datumRezervacije = datumRezervacije; }
    public int getSerijaId() { return serijaId; }
    public void setSerijaId(int serijaId) { this.serijaId = serijaId; }

    @Override
    public String toString() {
        return "Rezervacija{id=" + id + ", korisnikId=" + korisnikId + ", terminId=" + terminId + ", status=" + status + "}";
    }
}