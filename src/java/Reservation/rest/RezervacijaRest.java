/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.rest;

import Reservation.data.Rezervacija;
import Reservation.exception.ReservationException;
import Reservation.service.RezervacijaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("rezervacija")
public class RezervacijaRest {

    private final RezervacijaService service = RezervacijaService.getInstance();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rezervacija getRezervacija(@PathParam("id") int id) throws ReservationException {
        return service.findRezervacija(id);
    }
    
    @GET
    @Path("/my/{korisnikId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rezervacija> myReservations(@PathParam("korisnikId") int korisnikId)
        throws ReservationException {
    return service.getRezervacijeZaKorisnika(korisnikId);
}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rezervacija> getAllRezervacije() throws ReservationException {
        return service.getAllRezervacije();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRezervacija(Rezervacija r) throws ReservationException {
        service.addRezervacija(r);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRezervacija(Rezervacija r) throws ReservationException {
        service.updateRezervacija(r);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRezervacija(@PathParam("id") int id) throws ReservationException {
        service.deleteRezervacija(id);
        return Response.ok().build();
    }
}