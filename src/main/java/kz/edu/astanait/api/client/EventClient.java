package kz.edu.astanait.api.client;

import kz.edu.astanait.models.Event;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static kz.edu.astanait.api.client.WebTargetGetter.getWebTarget;

public class EventClient {
    private final String postURL = "event";

    public Event[] getAll() {
        return getWebTarget(postURL).path("/getAll")
                .request(MediaType.APPLICATION_JSON).get(Event[].class);
    }

    public void add(Event event) {
        getWebTarget(postURL).request().post(Entity.entity(event, MediaType.APPLICATION_JSON), Response.class);
    }

    public void update(Event event){
        getWebTarget(postURL).request().put(Entity.entity(event, MediaType.APPLICATION_JSON), Response.class);
    }

    public void delete(Event event) {
        getWebTarget(postURL).path("/delete").request().post(Entity.entity(event, MediaType.APPLICATION_JSON), Response.class);
    }
}
