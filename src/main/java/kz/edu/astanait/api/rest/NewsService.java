package kz.edu.astanait.api.rest;
import kz.edu.astanait.models.News;
import kz.edu.astanait.repositories.implementations.NewsRepository;
import kz.edu.astanait.repositories.interfaces.INewsRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/news")
public class NewsService {
    private final INewsRepository newsRepository = new NewsRepository();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<News> news = newsRepository.getAll();
        return Response.ok(news).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(News news) {
        try {
            newsRepository.add(news);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(News news) {
        try {
            newsRepository.update(news);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("News successfully modified.").build();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(News news) {
        try {
            newsRepository.delete(news);
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok("News successfully deleted.").build();
    }
}
