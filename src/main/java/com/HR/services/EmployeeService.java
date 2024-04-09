package com.HR.services;

import com.HR.dto.EmployeeBenefitDto;
import com.HR.dto.EmployeeDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.*;
import com.HR.persistence.repo.BenefitRepo;
import com.HR.persistence.repo.EmployeeBenefitRepo;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.util.mappers.EmployeeMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class EmployeeService {


    public List<EmployeeDto> getAllEmployees(int page, int size) {
        return Database.doInTransaction(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            List<Employee> employees = employeeRepo.findAll(page, size);
            return EmployeeMapper.INSTANCE.toDtoList(employees);
        });
    }

    public EmployeeDto getEmployee(int id) {
        return Database.doInTransaction(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Employee employee = employeeRepo.findOne(id).orElseThrow();
            return EmployeeMapper.INSTANCE.toDto(employee);
        });
    }

    public void createEmployee(EmployeeDto employee) {
        Database.doInTransactionWithoutResult(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            employeeRepo.create(EmployeeMapper.INSTANCE.toEntity(employee));
        });
    }

    public void updateEmployee(EmployeeDto employee, int id) {
        Database.doInTransactionWithoutResult(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Employee employeeEntity = employeeRepo.findOne(id).orElseThrow();
            employeeEntity.setFirstName(employee.getFirstName());
            employeeEntity.setLastName(employee.getLastName());
            Department department = new Department();
            department.setId(employee.getDepartmentID());
            employeeEntity.setDepartment(department);
            Employee directManager = new Employee();
            directManager.setId(employee.getDirectManagerID());
            employeeEntity.setDirectManager(directManager);
            Team team = new Team();
            team.setId(employee.getTeamID());
            employeeEntity.setTeam(team);
            employeeEntity.setTotalVacationDaysAllotted(employee.getTotalVacationDaysAllotted());
            employeeRepo.update(employeeEntity);
        });
    }

    public void deleteEmployee(int id) {
        Database.doInTransactionWithoutResult(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Employee employee = employeeRepo.findOne(id).orElseThrow();
            employeeRepo.delete(employee);
        });
    }

    public Set<EmployeeDto> getManagedEmployees(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow();
            Set<Employee> managedEmployees = employee.getManagedEmployees();
            return EmployeeMapper.INSTANCE.toDtoSet(managedEmployees);
        });
    }

    public Set<EmployeeBenefitDto> getEmployeeBenefits(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow();
            Set<EmployeeBenefitDto> employeeBenefits = EmployeeMapper.INSTANCE.toEmployeeBenefitDtoSet(employee.getEmployeeBenefits());
            return employeeBenefits;
        });
    }

    public void addEmployeeBenefit(int employeeId, int benefitId) {
        Database.doInTransactionWithoutResult(em -> {
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            BenefitRepo benefitRepo = new BenefitRepo(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow();
            Benefit benefit = benefitRepo.findOne(benefitId).orElseThrow();
            EmployeeBenefit employeeBenefit = new EmployeeBenefit(employee,benefit, LocalDate.now());
            employee.addEmployeeBenefit(employeeBenefit);
            EmployeeBenefitRepo employeeBenefitRepo = new EmployeeBenefitRepo(em);
            employeeBenefitRepo.create(employeeBenefit);
        });
    }
}
