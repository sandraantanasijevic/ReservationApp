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

public class RezervacionaSerija implements Serializable {
    private int id;
    private String tip;
    private Date doDatum;

    public RezervacionaSerija() {}

    public RezervacionaSerija(int id, String tip, Date doDatum) {
        this.id = id;
        this.tip = tip;
        this.doDatum = doDatum;
    }

    public RezervacionaSerija(String tip, Date doDatum) {
        this.tip = tip;
        this.doDatum = doDatum;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public Date getDoDatum() { return doDatum; }
    public void setDoDatum(Date doDatum) { this.doDatum = doDatum; }

    @Override
    public String toString() {
        return "RezervacionaSerija{id=" + id + ", tip=" + tip + ", doDatum=" + doDatum + "}";
    }
}
