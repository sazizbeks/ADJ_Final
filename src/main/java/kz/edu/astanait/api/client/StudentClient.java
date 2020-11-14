package kz.edu.astanait.api.client;

import kz.edu.astanait.models.Group;
import kz.edu.astanait.models.Major;
import kz.edu.astanait.models.Student;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;

import static kz.edu.astanait.api.client.WebTargetGetter.getWebTarget;

public class StudentClient {
    private final String postURL = "student";

    public Student getById(int id) {

        return getWebTarget(postURL).path("getById/" + id).request().accept(MediaType.APPLICATION_JSON).get(Student.class);
    }

    public Student[] getAll() {
        return getWebTarget(postURL).path("getAll").request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByGroup(Group group) {
        return getWebTarget(postURL).path("getByGroup/" + group.getMajor_id() + '/' + group.getGroup_number())
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByMajor(Major major) {
        return getWebTarget(postURL).path("getByMajor/" + major.getId())
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByYear(int year) {
        return getWebTarget(postURL).path("getByYear/" + year)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByFName(String fname) {
        return getWebTarget(postURL).path("getByFName/" + fname)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student[] getByLName(String lname) {
        return getWebTarget(postURL).path("getByLName/" + lname)
                .request().accept(MediaType.APPLICATION_JSON).get(Student[].class);
    }

    public Student getByUsername(String username) {
        try {
            return getWebTarget(postURL).path("getByUsername/" + username)
                    .request().accept(MediaType.APPLICATION_JSON).get(Student.class);

        } catch (Exception e) {
            throw new BadRequestException("Student with such username has not found");
        }
    }
}
