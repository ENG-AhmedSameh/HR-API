package com.HR.controllers.rest;

import com.HR.dto.DepartmentDto;
import com.HR.services.DepartmentService;
import jakarta.ws.rs.Path;

@Path("departments")
public class DepartmentController extends ResourceController<DepartmentDto, DepartmentService>{
    public DepartmentController(){
        super(new DepartmentService());
    }
}
