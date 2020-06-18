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

import ru.surpavel.university.domain.Chair;
import ru.surpavel.university.domain.Group;
import ru.surpavel.university.services.ChairService;
import ru.surpavel.university.services.GroupService;

@RestController
@Path("/chairs")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ChairRestController {

    private ChairService chairService;

    private GroupService groupService;

    @Autowired
    public ChairRestController(ChairService chairService, GroupService groupService) {
        this.chairService = chairService;
        this.groupService = groupService;
    }

    @POST
    public Response create(Chair chair, @Context UriInfo uriInfo) {
        chair = chairService.create(chair);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(chair.getId())).build();
        return Response.created(uri).entity(chair).build();
    }

    @GET
    public List<Chair> findAll() {
        return chairService.findAll();
    }

    @GET
    @Path("{chairId}")
    public Chair findById(@PathParam("chairId") int chairId) {
        return chairService.findById(chairId);
    }

    @GET
    @Path("{chairId}/groups")
    public List<Group> findChairGroups(@PathParam("chairId") int chairId) {
        return groupService.findByChairId(chairId);
    }

    @PUT
    @Path("{chairId}")
    public Response update(@PathParam("chairId") int chairId, Chair chair) {
        chair.setId(chairId);
        return Response.status(Status.OK).entity(chairService.update(chair)).build();
    }

    @DELETE
    @Path("{chairId}")
    public Response delete(@PathParam("chairId") int chairId) {
        chairService.delete(chairId);
        return Response.status(Status.NO_CONTENT).build();
    }

}
