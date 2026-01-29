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
import java.sql.Date;
import java.sql.Time;

public class Termin implements Serializable {
    private int id;
    private Date datum;
    private Time vremeOd;
    private Time vremeDo;
    private int resursId; // može i Resurs objekat ako želiš join

    public Termin() {}

    public Termin(int id, Date datum, Time vremeOd, Time vremeDo, int resursId) {
        this.id = id;
        this.datum = datum;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.resursId = resursId;
    }

    public Termin(Date datum, Time vremeOd, Time vremeDo, int resursId) {
        this.datum = datum;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.resursId = resursId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }
    public Time getVremeOd() { return vremeOd; }
    public void setVremeOd(Time vremeOd) { this.vremeOd = vremeOd; }
    public Time getVremeDo() { return vremeDo; }
    public void setVremeDo(Time vremeDo) { this.vremeDo = vremeDo; }
    public int getResursId() { return resursId; }
    public void setResursId(int resursId) { this.resursId = resursId; }

    @Override
    public String toString() {
        return "Termin{id=" + id + ", datum=" + datum + ", vremeOd=" + vremeOd + ", resursId=" + resursId + "}";
    }
}
