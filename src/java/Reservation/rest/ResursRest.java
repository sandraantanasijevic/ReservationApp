/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation.rest;

import Reservation.data.Resurs;
import Reservation.exception.ReservationException;
import Reservation.service.ResursService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("resurs")
public class ResursRest {

    private final ResursService service = ResursService.getInstance();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Resurs getResurs(@PathParam("id") int id) throws ReservationException {
        return service.findResurs(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resurs> getAllResursi() throws ReservationException {
        return service.getAllResursi();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResurs(Resurs r) throws ReservationException {
        service.addResurs(r);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateResurs(Resurs r) throws ReservationException {
        service.updateResurs(r);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteResurs(@PathParam("id") int id) throws ReservationException {
        service.deleteResurs(id);
        return Response.ok().build();
    }
}