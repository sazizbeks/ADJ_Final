package kz.edu.astanait.api.rest;
import kz.edu.astanait.models.Group;
import kz.edu.astanait.repositories.implementations.GroupRepository;
import kz.edu.astanait.repositories.interfaces.IGroupRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/group")
public class GroupService {
    private final IGroupRepository groupRepository = new GroupRepository();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Group> groups = groupRepository.getAll();
        return Response.ok(groups).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Group group) {
        try {
            groupRepository.add(group);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Group[] groups) {
        Group oldGroup = groups[0], newGroup = groups[1];
        try {
            groupRepository.update(oldGroup, newGroup);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Group successfully modified.").build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Group group) {
        try {
            groupRepository.delete(group);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("Group successfully deleted.").build();
    }

}
