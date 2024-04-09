package com.HR.controllers.rest;


import com.HR.dto.EmployeeBenefitDto;
import com.HR.dto.EmployeeDto;
import com.HR.persistence.entities.Benefit;
import com.HR.services.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Set;

@Path("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(){
        employeeService = new EmployeeService();
    }

    @GET
    public Response getEmployees(@DefaultValue("1") @QueryParam("page") int page,
                                 @DefaultValue("10") @QueryParam("size") int size){
        List<EmployeeDto> employeesResponse = employeeService.getAllEmployees(page, size);
        return Response.ok(employeesResponse).build();
    }
    @GET
    @Path("{id}")
    public Response getEmployee(@PathParam("id") int id){
        EmployeeDto employeeResponse = employeeService.getEmployee(id);
        return Response.ok(employeeResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(EmployeeDto employee){
        employeeService.createEmployee(employee);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmployeeDto employee, @PathParam("id") int id){
        employeeService.updateEmployee(employee, id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") int id){
        employeeService.deleteEmployee(id);
        return Response.ok().build();
    }

    @GET
    @Path("{employeeId}/managedEmployees")
    public Response getManagedEmployees(@PathParam("employeeId") int employeeId){
        Set<EmployeeDto> managedEmployees = employeeService.getManagedEmployees(employeeId);
        System.out.println(managedEmployees);
        return Response.ok(managedEmployees).build();
    }

    @GET
    @Path("{employeeId}/benefits")
    public Response getEmployeeBenefits(@PathParam("employeeId") int employeeId){
        Set<EmployeeBenefitDto> employeeBenefits = employeeService.getEmployeeBenefits(employeeId);
        return Response.ok(employeeBenefits).build();
    }

    @POST
    @Path("{employeeId}/benefits/{benefitId}")
    public Response addEmployeeBenefit(@PathParam("employeeId") int employeeId, @PathParam("benefitId") int benefitId){
        employeeService.addEmployeeBenefit(employeeId, benefitId);
        return Response.status(Response.Status.CREATED).build();
    }


}
