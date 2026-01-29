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
import java.sql.Time;

public class Resurs implements Serializable {
    private int id;
    private String naziv;
    private String tip;
    private Time radnoOd;
    private Time radnoDo;

    public Resurs() {}

    public Resurs(int id, String naziv, String tip, Time radnoOd, Time radnoDo) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
        this.radnoOd = radnoOd;
        this.radnoDo = radnoDo;
    }

    public Resurs(String naziv, String tip, Time radnoOd, Time radnoDo) {
        this.naziv = naziv;
        this.tip = tip;
        this.radnoOd = radnoOd;
        this.radnoDo = radnoDo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public Time getRadnoOd() { return radnoOd; }
    public void setRadnoOd(Time radnoOd) { this.radnoOd = radnoOd; }
    public Time getRadnoDo() { return radnoDo; }
    public void setRadnoDo(Time radnoDo) { this.radnoDo = radnoDo; }

    @Override
    public String toString() {
        return "Resurs{id=" + id + ", naziv=" + naziv + ", tip=" + tip + "}";
    }
}