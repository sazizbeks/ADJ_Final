package kz.edu.astanait.api.rest;

import kz.edu.astanait.models.Event;
import kz.edu.astanait.repositories.implementations.EventRepository;
import kz.edu.astanait.repositories.interfaces.IEventRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class EventService {
    private final IEventRepository eventRepository = new EventRepository();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Event> events = eventRepository.getAll();
        return Response.ok(events).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Event event) {
        try {
            eventRepository.add(event);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Event event) {
        try {
            eventRepository.update(event);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Event successfully modified.").build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Event event) {
        try {
            eventRepository.delete(event);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Event successfully deleted.").build();
    }
}
