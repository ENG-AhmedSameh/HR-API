package com.HR.controllers.rest;

import com.HR.dto.*;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.services.EmployeeService;
import com.HR.util.mappers.EmployeeMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("employees")
public class EmployeeController extends ResourceController<EmployeeDto, EmployeeService>{
    private final EmployeeService employeeService;

    public EmployeeController(){
        super(new EmployeeService(new EmployeeRepo(),EmployeeMapper.INSTANCE));
        employeeService = new EmployeeService(new EmployeeRepo(),EmployeeMapper.INSTANCE);
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
