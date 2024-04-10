package com.HR.controllers.rest;

import com.HR.dto.VacationDto;
import com.HR.services.VacationService;
import jakarta.ws.rs.Path;

@Path("vacations")
public class VacationController extends ResourceController<VacationDto, VacationService>{
    protected VacationController() {
        super(new VacationService());
    }
}
