package com.HR.controllers.rest;

import com.HR.dto.*;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(@DefaultValue("1") @QueryParam("page") int page,
                                 @DefaultValue("10") @QueryParam("size") int size){
        List<EmployeeDto> employeesResponse = employeeService.getAllEmployees(page, size);
        return Response.ok(employeesResponse).build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") int id){
        EmployeeDto employeeResponse = employeeService.getEmployee(id);
        return Response.ok(employeeResponse).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(EmployeeDto employee){
        EmployeeDto employeeDto = employeeService.createEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(employeeDto).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmployeeDto employee, @PathParam("id") int id){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employee, id);
        return Response.ok().entity(updatedEmployee).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") int id){
        employeeService.deleteEmployee(id);
        return Response.ok().build();
    }

    @GET
    @Path("{employeeId}/managedEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getManagedEmployees(@PathParam("employeeId") int employeeId){
        Set<EmployeeDto> managedEmployees = employeeService.getManagedEmployees(employeeId);
        System.out.println(managedEmployees);
        return Response.ok(managedEmployees).build();
    }

    @GET
    @Path("{employeeId}/benefits")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeBenefits(@PathParam("employeeId") int employeeId){
        Set<BenefitDto> employeeBenefits = employeeService.getEmployeeBenefits(employeeId);
        return Response.ok(employeeBenefits).build();
    }

    @POST
    @Path("{employeeId}/benefits/{benefitId}")
    public Response addEmployeeBenefit(@PathParam("employeeId") int employeeId, @PathParam("benefitId") int benefitId){
        employeeService.addEmployeeBenefit(employeeId, benefitId);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{employeeId}/benefits/{benefitId}")
    public Response removeEmployeeBenefit(@PathParam("employeeId") int employeeId, @PathParam("benefitId") int benefitId){
        employeeService.removeEmployeeBenefit(employeeId, benefitId);
        return Response.ok().build();
    }

    @GET
    @Path("{employeeId}/leadedTeams")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeadedTeams(@PathParam("employeeId") int employeeId){
        Set<TeamDto> leadedTeams = employeeService.getLeadedTeams(employeeId);
        return Response.ok(leadedTeams).build();
    }

    @GET
    @Path("{employeeId}/contracts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContracts(@PathParam("employeeId") int employeeId){
        Set<ContractDto> contracts = employeeService.getContracts(employeeId);
        return Response.ok(contracts).build();
    }

    @GET
    @Path("{employeeId}/contracts/{contractId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContract(@PathParam("employeeId") int employeeId, @PathParam("contractId") int contractId){
        ContractDto contract = employeeService.getContract(employeeId, contractId);
        return Response.ok(contract).build();
    }

    @GET
    @Path("{employeeId}/vacations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacations(@PathParam("employeeId") int employeeId){
        Set<VacationDto> vacations = employeeService.getVacations(employeeId);
        return Response.ok(vacations).build();
    }

    @GET
    @Path("{employeeId}/vacations/{vacationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacation(@PathParam("employeeId") int employeeId, @PathParam("vacationId") int vacationId){
        VacationDto vacation = employeeService.getVacation(employeeId, vacationId);
        return Response.ok(vacation).build();
    }
}
