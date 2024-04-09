package com.HR.controllers.rest;

import com.HR.dto.BenefitDto;
import com.HR.services.BenefitService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("benefits")
public class BenefitController {
    private final BenefitService benefitService;

    public BenefitController(){
        benefitService = new BenefitService();
    }


    @GET
    @Path("{id}")
    public Response getBenefit(@PathParam("id") int id){
        BenefitDto benefitDto = benefitService.getBenefit(id);
        return Response.ok(benefitDto).build();
    }
}
