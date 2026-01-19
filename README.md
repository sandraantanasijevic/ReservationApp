# ReservationApp

1. Opis projekta
Aplikacija ReservationApp je serverska aplikacija koja omogućava upravljanje rezervacijama termina i resursa u poslovnom centru.
Implementirana je u Java SE koristeći NetBeans, a testirana kroz Postman i phpMyAdmin.

Glavne funkcionalnosti:
Logovanje korisnika
Pregled resursa i slobodnih termina
Kreiranje, izmena i otkazivanje rezervacija
Kreiranje ponavljajućih rezervacija (serija)
Pregled sopstvenih rezervacija

2. Struktura projekta
Projektna struktura je organizovana u pakete:

ReservationApp/
├── dao/      
├── data/    
├── rest/     
├── service/     
├── exception/
└── ReservationDemo.java  

2.1 Reservation.dao
Klase koje komuniciraju sa bazom:
KorisnikDao, ResursDao, TerminDao, RezervacijaDao, RezervacionaSerijaDao

2.2 Reservation.data
Model klase entiteta:
Korisnik, Resurs, Termin, Rezervacija, RezervacionaSerija
RezervacionaSerija ima tip i doDatum; serijaId povezuje rezervacije sa serijom.

2.3 Reservation.service
Servisi sa biznis logikom:
KorisnikService, ResursService, TerminService, RezervacijaService, RezervacionaSerijaService
Singleton pattern (getInstance()), koristi Dao sloj.

2.4 Reservation.rest
REST kontroleri:
KorisnikRest, ResursRest, TerminRest, RezervacijaRest, RezervacionaSerijaRest
Putanje: /api/korisnik, /api/resurs, /api/termin, /api/rezervacija, /api/serija

2.5 Reservation.exception
ReservationException – za greške pri radu sa bazom i servisima.

2.6 ReservationDemo.java
Demo klasa za testiranje:
Dodaje korisnike, resurse, termine
Kreira rezervacije i ponavljajuće serije (NEDELJNA, SVAKODNEVNA, GODISNJA)
Prikazuje rezervacije u konzoli

3. Tehnologije i alati

Java SE 8 
NetBeans IDE – razvojno okruženje za pisanje i pokretanje Java projekta.
GlassFish 4.1 – server
MySQL / phpMyAdmin – baza podataka 
Postman – alat za testiranje REST API poziva

3.1 Rad sa bazom

Kreirane su tabele: korisnik, resurs, termin, rezervacija, rezervaciona_serija.
Testiranje upisa i čitanja podataka je izvršeno kroz Postman i phpMyAdmin.

3.2 Testiranje API-ja

Postman je korišćen za testiranje svih endpointova (korisnik, resurs, termin, rezervacija, serija).
Primeri test poziva:

POST	http://localhost:8080/ReservationApp/api/korisnik	Dodavanje novog korisnika
GET	http://localhost:8080/ReservationApp/api/resurs	Prikaz svih resursa
GET	http://localhost:8080/ReservationApp/api/termin/slobodni?datum=2026-01-20&resursId=6	Prikaz slobodnih termina za resurs na taj datum
POST	http://localhost:8080/ReservationApp/api/rezervacija	Kreiranje rezervacije

Putanje API-ja su prilagođene:

/api/korisnik umesto /api/auth
/api/resurs umesto /api/resources
/api/termin/slobodni umesto /api/resources/{id}/slots
/api/rezervacija umesto /api/reservations
/api/rezervacija/id umesto /api/reservation
/api/serija umesto /api/reservations/series
/api/rezervacija/my/{korisnikId} umesto /api/myReservations

Polja JSON objekata su prevedena i prilagođena radi preglednosti (naziv, radnoOd, radnoDo, tip, datum, vremeOd, vremeDo).
Slobodni termini se filtriraju po datumu i resursu koristeći query parametre (datum, resursId).
Ponavljajuće rezervacije imaju polja tip i doDatum koja definišu učestalost i kraj serije (NEDELJNA, SVAKODNEVNA, GODISNJA).

Sve izmene su napravljene radi bolje preglednosti i testiranja, a API sada omogućava sve funkcionalnosti zadatka: logovanje korisnika, pregled resursa, pregled slobodnih termina, kreiranje i izmenu rezervacija, otkazivanje, pregled sopstvenih rezervacija i kreiranje ponavljajućih rezervacija.
