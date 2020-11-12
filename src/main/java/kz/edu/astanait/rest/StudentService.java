package kz.edu.astanait.rest;

import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Student;
import kz.edu.astanait.repositories.implementations.StudentRepository;
import kz.edu.astanait.repositories.interfaces.IStudentRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentService {

    private final IStudentRepository studentRepository = new StudentRepository();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Student> list = studentRepository.getAll();
        return Response.status(Response.Status.FOUND).entity(list).build();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getId(@PathParam("id") Integer id) {
        Student student;
        try {
            student = studentRepository.findById(id);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(student).build();
    }
}
