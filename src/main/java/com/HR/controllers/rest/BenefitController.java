package com.HR.controllers.rest;

import com.HR.dto.BenefitDto;
import com.HR.services.BenefitService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("benefits")
public class BenefitController {
    private final BenefitService benefitService;

    public BenefitController(){
        benefitService = new BenefitService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenefits(@DefaultValue("1") @QueryParam("page") int page,
                                @DefaultValue("10") @QueryParam("size") int size){
        List<BenefitDto> benefits = benefitService.getAllBenefits(page, size);
        return Response.ok(benefits).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenefit(@PathParam("id") int id){
        BenefitDto benefitDto = benefitService.getBenefit(id);
        return Response.ok(benefitDto).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBenefit(BenefitDto benefitDto){
        BenefitDto benefit = benefitService.createBenefit(benefitDto);
        return Response.status(Response.Status.CREATED).entity(benefit).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBenefit(BenefitDto benefit, @PathParam("id") int id){
        BenefitDto updatedBenefit = benefitService.updateBenefit(benefit, id);
        return Response.ok().entity(updatedBenefit).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteBenefit(@PathParam("id") int id){
        benefitService.deleteBenefit(id);
        return Response.ok().build();
    }
}
