/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Reservation.data.Korisnik;
import Reservation.exception.ReservationException;
import Reservation.service.KorisnikService;

@Path("korisnik")
public class KorisnikRest {

    private final KorisnikService service = KorisnikService.getInstance();

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnik(@PathParam("username") String username) throws ReservationException {
        return service.findKorisnik(username);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKorisnik(Korisnik k) throws ReservationException {
        service.addNewKorisnik(k);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKorisnik(Korisnik k) throws ReservationException {
        service.updateKorisnik(k);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKorisnik(@PathParam("username") String username) throws ReservationException {
        Korisnik k = service.findKorisnik(username);
        if(k != null) service.deleteKorisnik(k);
        return Response.ok().build();
    }
}