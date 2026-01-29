/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.rest;

import Reservation.data.RezervacionaSerija;
import Reservation.exception.ReservationException;
import Reservation.service.RezervacionaSerijaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("serija")
public class RezervacionaSerijaRest {

    private final RezervacionaSerijaService service = RezervacionaSerijaService.getInstance();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RezervacionaSerija getSerija(@PathParam("id") int id) throws ReservationException {
        return service.findSerija(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RezervacionaSerija> getAllSerije() throws ReservationException {
        return service.getAllSerije();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSerija(RezervacionaSerija s) throws ReservationException {
        service.addSerija(s);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSerija(RezervacionaSerija s) throws ReservationException {
        service.updateSerija(s);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSerija(@PathParam("id") int id) throws ReservationException {
        service.deleteSerija(id);
        return Response.ok().build();
    }
}