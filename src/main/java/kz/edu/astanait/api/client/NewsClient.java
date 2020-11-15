package kz.edu.astanait.api.client;


import kz.edu.astanait.models.News;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static kz.edu.astanait.api.client.WebTargetGetter.getWebTarget;

public class NewsClient {
    private final String postURL = "news";

    public News[] getAll() {
        return getWebTarget(postURL).path("/getAll")
                .request(MediaType.APPLICATION_JSON).get(News[].class);
    }

    public void add(News news) {
        getWebTarget(postURL).request().post(Entity.entity(news, MediaType.APPLICATION_JSON), Response.class);
    }

    public void update(News news){
        getWebTarget(postURL).request().put(Entity.entity(news, MediaType.APPLICATION_JSON), Response.class);
    }

    public void delete(News news) {
        getWebTarget(postURL).path("/delete").request().post(Entity.entity(news, MediaType.APPLICATION_JSON), Response.class);
    }
}
