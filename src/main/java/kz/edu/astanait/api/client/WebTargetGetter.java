package kz.edu.astanait.api.client;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class WebTargetGetter {
    private WebTargetGetter(){}

    public static WebTarget getWebTarget(String postURL) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        String baseURI = "http://localhost:8080/Final_war_exploded/api/"+postURL;
        return client.target(baseURI);
    }
}
