package kz.edu.astanait.rest;

import kz.edu.astanait.exceptions.NotFoundException;
import kz.edu.astanait.models.Group;
import kz.edu.astanait.models.Major;
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
    @Path("/getById/{id}")
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

    @GET
    @Path("/getByGroup/{group}/{groupNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByGroup(@PathParam("group") String group,
                               @PathParam("groupNumber") Integer groupNumber) {
        List<Student> list;
        try {
            list = studentRepository.findByGroup(new Group(group, groupNumber));
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/getByMajor/{major}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMajor(@PathParam("major") String major) {
        List<Student> list;
        try {
            list = studentRepository.findByMajor(new Major(major));
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/getByYear/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByYear(@PathParam("year") int year) {
        List<Student> list;
        try {
            list = studentRepository.findByYear(year);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/getByFName/{fname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByFName(@PathParam("fname") String fname) {
        List<Student> list;
        try {
            list = studentRepository.findByFName(fname);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

    @GET
    @Path("/getByLName/{lname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLName(@PathParam("lname") String lname) {
        List<Student> list;
        try {
            list = studentRepository.findByLName(lname);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(list).build();
    }

}
