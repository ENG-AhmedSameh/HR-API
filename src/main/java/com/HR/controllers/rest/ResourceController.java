package com.HR.controllers.rest;

import com.HR.services.GenericResourceService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public abstract class ResourceController<T, S extends GenericResourceService<T,?,?>> {

    protected final S service;

    protected ResourceController(S service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResources(@DefaultValue("1") @QueryParam("page") int page,
                                 @DefaultValue("10") @QueryParam("size") int size) {
        List<T> resources = service.getAll(page, size);
        return Response.ok(resources).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResource(@PathParam("id") int id) {
        T resource = service.get(id);
        return Response.ok(resource).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResource(T resourceDto) {
        T resource = service.create(resourceDto);
        return Response.status(Response.Status.CREATED).entity(resource).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateResource(T resource, @PathParam("id") int id) {
        T updatedResource = service.update(resource, id);
        return Response.ok().entity(updatedResource).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") int id) {
        service.delete(id);
        return Response.ok().build();
    }
}
