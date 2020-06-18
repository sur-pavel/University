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

import ru.surpavel.university.domain.Group;
import ru.surpavel.university.domain.Student;
import ru.surpavel.university.services.GroupService;
import ru.surpavel.university.services.StudentService;

@RestController
@Path("/groups")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class GroupRestController {

    private GroupService groupService;

    private StudentService studentService;

    @Autowired
    public GroupRestController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @POST
    public Response create(Group group, @Context UriInfo uriInfo) {
        group = groupService.create(group);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(group.getId())).build();
        return Response.created(uri).entity(group).build();
    }

    @GET
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GET
    @Path("{groupId}")
    public Group findById(@PathParam("groupId") int groupId) {
        return groupService.findById(groupId);
    }

    @GET
    @Path("{groupId}/students")
    public List<Student> findGroupStudents(@PathParam("groupId") int groupId) {
        return studentService.findByGroupId(groupId);
    }

    @PUT
    @Path("{groupId}")
    public Response update(@PathParam("groupId") int groupId, Group group) {
        group.setId(groupId);
        return Response.status(Status.OK).entity(groupService.update(group)).build();
    }

    @DELETE
    @Path("{groupId}")
    public Response delete(@PathParam("groupId") int groupId) {
        groupService.delete(groupId);
        return Response.status(Status.NO_CONTENT).build();
    }

}
