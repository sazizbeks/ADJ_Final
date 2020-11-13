package kz.edu.astanait.api.client;

import com.google.gson.Gson;
import kz.edu.astanait.models.Group;
import kz.edu.astanait.models.Major;
import kz.edu.astanait.models.Student;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class StudentClient {
    private final Gson gson = new Gson();

    private WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        String baseURI = "http://localhost:8080/Final_war_exploded/api/student/";
        return client.target(baseURI);
    }

    public Student getById(int id) {
        return getWebTarget().path("getById/" + id).request().accept(MediaType.APPLICATION_JSON).get(Student.class);
    }

    public Student[] getAll() {
        return getWebTarget().path("getAll").request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByGroup(Group group) {
        return getWebTarget().path("getByGroup/" + group.getMajor_id() + '/' + group.getGroup_number())
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByMajor(Major major) {
        return getWebTarget().path("getByMajor/" + major.getId())
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByYear(int year) {
        return getWebTarget().path("getByYear/" + year)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByFName(String fname) {
        return getWebTarget().path("getByFName/" + fname)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByLName(String lname) {
        return getWebTarget().path("getByLName/" + lname)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }
}
