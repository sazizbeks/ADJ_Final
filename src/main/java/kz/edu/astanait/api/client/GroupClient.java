package kz.edu.astanait.api.client;

import kz.edu.astanait.models.Group;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static kz.edu.astanait.api.client.WebTargetGetter.getWebTarget;

public class GroupClient {
    private final String postURL = "group";

    public Group[] getAll() {
        return getWebTarget(postURL).path("/getAll")
                .request(MediaType.APPLICATION_JSON).get(Group[].class);
    }

    public void add(Group group) {
        getWebTarget(postURL).request().post(Entity.entity(group, MediaType.APPLICATION_JSON), Response.class);
    }

    public void update(Group oldGroup, Group newGroup){
        Group[]groups = {oldGroup, newGroup};
        getWebTarget(postURL).request().put(Entity.entity(groups, MediaType.APPLICATION_JSON), Response.class);
    }

    public void delete(Group group) {
        getWebTarget(postURL).path("/delete").request().post(Entity.entity(group, MediaType.APPLICATION_JSON), Response.class);
    }
}
