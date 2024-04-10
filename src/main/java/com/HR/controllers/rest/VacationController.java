package com.HR.controllers.rest;

import com.HR.dto.VacationDto;
import com.HR.services.VacationService;

public class VacationController extends ResourceController<VacationDto, VacationService>{
    protected VacationController() {
        super(new VacationService());
    }
}
