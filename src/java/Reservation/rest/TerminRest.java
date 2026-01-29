/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.rest;

import Reservation.data.Termin;
import Reservation.exception.ReservationException;
import Reservation.service.TerminService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.sql.Date;

@Path("termin")
public class TerminRest {

    private final TerminService service = TerminService.getInstance();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Termin getTermin(@PathParam("id") int id) throws ReservationException {
        return service.findTermin(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Termin> getAllTermini() throws ReservationException {
        return service.getAllTermini();
    }
    
    @GET
    @Path("/slobodni")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Termin> getSlobodniTermini(
        @QueryParam("datum") String datum,
        @QueryParam("resursId") int resursId
    ) throws ReservationException {

        Date d = Date.valueOf(datum);
        return service.getSlobodniTermini(d, resursId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTermin(Termin t) throws ReservationException {
        service.addTermin(t);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTermin(Termin t) throws ReservationException {
        service.updateTermin(t);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTermin(@PathParam("id") int id) throws ReservationException {
        service.deleteTermin(id);
        return Response.ok().build();
    }
}