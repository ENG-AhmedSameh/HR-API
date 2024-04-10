package com.HR.controllers.rest;

import com.HR.dto.BenefitDto;
import com.HR.services.BenefitService;
import jakarta.ws.rs.Path;

@Path("benefits")
public class BenefitController extends ResourceController<BenefitDto, BenefitService>{
    public BenefitController(){
        super(new BenefitService());
    }
}
