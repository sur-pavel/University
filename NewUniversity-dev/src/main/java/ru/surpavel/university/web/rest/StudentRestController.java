package ru.surpavel.university.web.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ru.surpavel.university.domain.Student;
import ru.surpavel.university.services.StudentService;

@RestController
@Path("/students")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class StudentRestController {

    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @POST
    public Response create(Student student, @Context UriInfo uriInfo) {
        student = studentService.create(student);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(student.getId())).build();
        return Response.created(uri).entity(student).build();
    }

    @GET
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GET
    @Path("{studentId}")
    public Student findById(@PathParam("studentId") int studentId) {
        return studentService.findById(studentId);
    }

    @PUT
    @Path("{studentId}")
    public Response update(@PathParam("studentId") int studentId, Student student) {
        student.setId(studentId);
        return Response.status(Status.OK).entity(studentService.update(student)).build();
    }

    @DELETE
    @Path("{studentId}")
    public Response delete(@PathParam("studentId") int studentId) {
        studentService.delete(studentId);
        return Response.status(Status.NO_CONTENT).build();
    }
}
