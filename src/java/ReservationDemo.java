/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sandra
 */
import Reservation.data.Resurs;
import Reservation.data.Termin;
import Reservation.data.Korisnik;
import Reservation.data.Rezervacija;
import Reservation.data.RezervacionaSerija;
import Reservation.exception.ReservationException;
import Reservation.service.ResursService;
import Reservation.service.TerminService;
import Reservation.service.RezervacijaService;
import Reservation.service.RezervacionaSerijaService;
import Reservation.service.KorisnikService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReservationDemo {

    private static final ResursService resursService = ResursService.getInstance();
    private static final TerminService terminService = TerminService.getInstance();
    private static final RezervacijaService rezervacijaService = RezervacijaService.getInstance();
    private static final RezervacionaSerijaService serijaService = RezervacionaSerijaService.getInstance();
    private static final KorisnikService korisnikService = KorisnikService.getInstance();

    public static void main(String[] args) throws Exception {

        Korisnik korisnik = korisnikService.findKorisnik("test");
        System.out.println("Ulogovan korisnik: " + korisnik);

        Resurs sala = new Resurs("Sala 4", "SALA",
                Time.valueOf("08:00:00"),
                Time.valueOf("20:00:00"));
        resursService.addResurs(sala);
        
        System.out.println("Dodati resursi:");
        for (Resurs r : resursService.getAllResursi()) {
        System.out.println(r);
        }

        Termin t1 = new Termin(
                Date.valueOf("2026-01-20"),
                Time.valueOf("19:00:00"),
                Time.valueOf("20:00:00"),
                sala.getId()
        );
        

        Termin t2 = new Termin(
                Date.valueOf("2026-01-20"),
                Time.valueOf("11:00:00"),
                Time.valueOf("12:00:00"),
                sala.getId()
        );
        Termin t3 = new Termin(
                Date.valueOf("2026-01-20"),
                Time.valueOf("17:00:00"),
                Time.valueOf("18:00:00"),
                sala.getId()
        );

        terminService.addTermin(t1);
        terminService.addTermin(t2);
        terminService.addTermin(t3);


        System.out.println("\nSvi termini:");
        for (Termin t : terminService.getAllTermini()) {
            System.out.println(t);
        }
        
        System.out.println("\nSlobodni termini:");
        for (Termin t : terminService.getSlobodniTermini(Date.valueOf("2026-01-20"), sala.getId())) {
            System.out.println(t);
        }

        RezervacionaSerija serija = new RezervacionaSerija(
                "NEDELJNA",
                Date.valueOf("2026-02-20")
        );
        serijaService.addSerija(serija);

        Rezervacija r1 = new Rezervacija(
                korisnik.getId(),
                t1.getId(),
                "AKTIVNA",
                serija.getId()
        );

        Rezervacija r2 = new Rezervacija(
                korisnik.getId(),
                t2.getId(),
                "AKTIVNA",
                serija.getId()
        );

        rezervacijaService.addRezervacija(r1);
        rezervacijaService.addRezervacija(r2);

        
        System.out.println("\n slozena");

        //kreiramo seriju
        RezervacionaSerija serija2 = new RezervacionaSerija(
            "NEDELJNA",
            Date.valueOf("2026-03-01")
        );

        //kreiramo rezervaciju
        Rezervacija rezervacija = new Rezervacija(
            korisnik.getId(),
            t1.getId(),
            "AKTIVNA",
            0
        );

        rezervacijaService.addRezervacijaWithSerija(rezervacija, serija2);

        System.out.println("Uspesno kreirana rezervacija sa serijom");


        System.out.println("\nRezervacije korisnika:");
        List<Rezervacija> sveRezervacije = rezervacijaService.getAllRezervacije();
        for (Rezervacija r : sveRezervacije) {
            System.out.println(r);
        }

        r1.setStatus("OTKAZANA");
        rezervacijaService.updateRezervacija(r1);

        System.out.println("\nRezervacija nakon otkazivanja:");
        System.out.println(rezervacijaService.findRezervacija(r1.getId()));
        
        System.out.println("\nRezervacije ulogovanog korisnika:");
        List<Rezervacija> rezervacijeKorisnika = rezervacijaService.getRezervacijeZaKorisnika(korisnik.getId());
        for (Rezervacija r : rezervacijeKorisnika) {
            System.out.println(r);
        }
    }
}


