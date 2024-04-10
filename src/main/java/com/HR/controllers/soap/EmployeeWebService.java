package com.HR.controllers.soap;

import com.HR.dto.*;
import com.HR.services.EmployeeService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Set;

@WebService
public class EmployeeWebService {

    private final EmployeeService service = new EmployeeService();

    @WebMethod
    public List<EmployeeDto> getEmployees(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public EmployeeDto getEmployee(@WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public EmployeeDto createEmployee(@WebParam(name = "employeeDto") EmployeeDto employeeDto) {
        return service.create(employeeDto);
    }

    @WebMethod
    public EmployeeDto updateEmployee(
            @WebParam(name = "employeeDto") EmployeeDto employeeDto) {
        return service.update(employeeDto, employeeDto.getId()==null?0:employeeDto.getId());
    }

    @WebMethod
    public void deleteEmployee(@WebParam(name = "id") int id) {
        service.delete(id);
    }



    @WebMethod
    public Set<EmployeeDto> getManagedEmployees(@WebParam(name = "employeeId") int employeeId) {
        return service.getManagedEmployees(employeeId);
    }

    @WebMethod
    public Set<BenefitDto> getEmployeeBenefits(@WebParam(name = "employeeId") int employeeId) {
        return service.getEmployeeBenefits(employeeId);
    }

    @WebMethod
    public void addEmployeeBenefit(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "benefitId") int benefitId) {
        service.addEmployeeBenefit(employeeId, benefitId);
    }

    @WebMethod
    public void removeEmployeeBenefit(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "benefitId") int benefitId) {
        service.removeEmployeeBenefit(employeeId, benefitId);
    }

    @WebMethod
    public Set<TeamDto> getAllLeadedTeams(@WebParam(name = "employeeId") int employeeId) {
        return service.getLeadedTeams(employeeId);
    }

    @WebMethod
    public Set<ContractDto> getAllEmployeeContracts(@WebParam(name = "employeeId") int employeeId) {
        return service.getContracts(employeeId);
    }

    @WebMethod
    public ContractDto getEmployeeContract(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "contractId") int contractId) {
        return service.getContract(employeeId, contractId);
    }

    @WebMethod
    public Set<VacationDto> getAllEmployeeVacations(@WebParam(name = "employeeId") int employeeId) {
        return service.getVacations(employeeId);
    }

    @WebMethod
    public VacationDto getEmployeeVacation(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "vacationId") int vacationId) {
        return service.getVacation(employeeId, vacationId);
    }
}
