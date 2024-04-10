package com.HR.services;

import com.HR.dto.*;
import com.HR.persistence.Database;
import com.HR.persistence.entities.*;
import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import com.HR.persistence.repo.*;
import com.HR.util.mappers.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService extends GenericResourceService<EmployeeDto,Employee,EmployeeRepo> {

    public EmployeeService(EmployeeRepo repo, GenericMapper<EmployeeDto, Employee> mapper) {
        super(repo, mapper);
    }

//    @Override
//    public List<EmployeeDto> getAll(int page, int size) {
//        return Database.doInTransaction(em->{
//            EmployeeRepo employeeRepo = new EmployeeRepo(em);
//            List<Employee> employees = employeeRepo.findAllWithPagination(page, size);
//            return EmployeeMapper.INSTANCE.toDtoList(employees);
//        });
//    }
//
//    @Override
//    public EmployeeDto get(int id) {
//        return Database.doInTransaction(em->{
//            EmployeeRepo employeeRepo = new EmployeeRepo(em);
//            Employee employee = employeeRepo.findOne(id).orElseThrow(()->new NoSuchElementException("No such employee found with id "+id));
//            return EmployeeMapper.INSTANCE.toDto(employee);
//        });
//    }
//
//    @Override
//    public EmployeeDto create(EmployeeDto employee) {
//        return Database.doInTransaction(em->{
//            EmployeeRepo employeeRepo = new EmployeeRepo(em);
//            Employee createdEmployee = EmployeeMapper.INSTANCE.toEntity(employee);
//            if(createdEmployee.getDirectManager()!=null){
//                if(createdEmployee.getDirectManager().getId()==null){
//                    createdEmployee.setDirectManager(null);
//                }else{
//                    Employee directManager = employeeRepo.findOne(createdEmployee.getDirectManager().getId()).orElseThrow(()->new NoSuchElementException("No such manager found with id "+employee.getDirectManagerID()));
//                    createdEmployee.setDirectManager(directManager);
//                }
//            }
//            employeeRepo.create(createdEmployee);
//            return EmployeeMapper.INSTANCE.toDto(createdEmployee);
//        });
//    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto, int id) {
        return Database.doInTransaction(em->{
            boolean hasChanges = false;
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employeeEntity = employeeRepo.findOne(id).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+id));
            if(employeeDto.getFirstName()!=null&& !employeeDto.getFirstName().equals(employeeEntity.getFirstName())){
                employeeEntity.setFirstName(employeeDto.getFirstName());
                hasChanges = true;
            }
            if(employeeDto.getLastName()!=null && !employeeDto.getLastName().equals(employeeEntity.getLastName())){
                employeeEntity.setLastName(employeeDto.getLastName());
                hasChanges = true;
            }
            if(employeeDto.getDirectManagerID()!=null && !employeeDto.getDirectManagerID().equals(employeeEntity.getDirectManager().getId())){
                Employee directManager = employeeRepo.findOne(employeeDto.getDirectManagerID()).orElseThrow(()->new NoSuchElementException("No such manager found with id "+employeeDto.getDirectManagerID()));
                employeeEntity.setDirectManager(directManager);
                hasChanges = true;
            }
            if(employeeDto.getTeamID()!=null && !employeeDto.getTeamID().equals(employeeEntity.getTeam().getId())){
                Team team = new TeamRepo().withEntityManager(em).findOne(employeeDto.getTeamID()).orElseThrow(()->new NoSuchElementException("No such team found with id "+employeeDto.getTeamID()));
                employeeEntity.setTeam(team);
                hasChanges = true;
            }
            if(employeeDto.getTotalVacationDaysAllotted()!=null && !employeeDto.getTotalVacationDaysAllotted().equals(employeeEntity.getTotalVacationDaysAllotted())){
                employeeEntity.setTotalVacationDaysAllotted(employeeDto.getTotalVacationDaysAllotted());
                hasChanges = true;
            }
            if(hasChanges)
                employeeRepo.update(employeeEntity);
            else
                throw new IllegalArgumentException("No changes found in the request body");
            return EmployeeMapper.INSTANCE.toDto(employeeEntity);
        });
    }

//    @Override
//    public void delete(int id) {
//        Database.doInTransactionWithoutResult(em->{
//            EmployeeRepo employeeRepo = new EmployeeRepo(em);
//            Employee employee = employeeRepo.findOne(id).orElseThrow(()->new NoSuchElementException("No such employee found with id "+id));
//            employeeRepo.delete(employee);
//        });
//    }

    public Set<EmployeeDto> getManagedEmployees(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(() -> new NoSuchElementException("No such employee found with id " + employeeId));
            Set<Employee> managedEmployees = employee.getManagedEmployees();
            return EmployeeMapper.INSTANCE.toDtoSet(managedEmployees);
        });
    }

    public Set<BenefitDto> getEmployeeBenefits(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->new NoSuchElementException("No such employee found with id "+employeeId));
            Set<Benefit> benefits = employee.getEmployeeBenefits().stream()
                    .map(EmployeeBenefit::getBenefit)
                    .collect(Collectors.toSet());
            return BenefitMapper.INSTANCE.toDtoSet(benefits);
        });
    }

    public void addEmployeeBenefit(int employeeId, int benefitId) {
        Database.doInTransactionWithoutResult(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            BenefitRepo benefitRepo = (BenefitRepo) new BenefitRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(() -> new NoSuchElementException("No such employee found with id " + employeeId));
            Benefit benefit = benefitRepo.findOne(benefitId).orElseThrow(() -> new NoSuchElementException("No such benefit found with id " + benefitId));
            EmployeeBenefit employeeBenefit = new EmployeeBenefit(employee,benefit, LocalDate.now());
            employee.addEmployeeBenefit(employeeBenefit);
            EmployeeBenefitRepo employeeBenefitRepo = (EmployeeBenefitRepo) new EmployeeBenefitRepo().withEntityManager(em);
            employeeBenefitRepo.create(employeeBenefit);
        });
    }

    public void removeEmployeeBenefit(int employeeId, int benefitId) {
        Database.doInTransactionWithoutResult(em -> {
            EmployeebenefitId employeebenefitId = new EmployeebenefitId(employeeId, benefitId);
            EmployeeBenefitRepo employeeBenefitRepo = (EmployeeBenefitRepo) new EmployeeBenefitRepo().withEntityManager(em);
            EmployeeBenefit employeeBenefit = employeeBenefitRepo.find(employeebenefitId).orElseThrow(() -> new NoSuchElementException("No benefit with id "+benefitId+ " found for employee with id " + employeeId));
            employeeBenefitRepo.delete(employeeBenefit);
        });
    }

    public Set<TeamDto> getLeadedTeams(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+employeeId));
            Set<Team> leadedTeams = employee.getLeadedTeams();
            return TeamMapper.INSTANCE.toDtoSet(leadedTeams);
        });
    }

    public Set<ContractDto> getContracts(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+employeeId));
            Set<Contract> contracts = employee.getContracts();
            return ContractMapper.INSTANCE.toDtoSet(contracts);
        });
    }

    public Set<VacationDto> getVacations(int employeeId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+employeeId));
            Set<Vacation> vacations = employee.getVacations();
            return VacationMapper.INSTANCE.toDtoSet(vacations);
        });
    }

    public ContractDto getContract(int employeeId, int contractId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+employeeId));
//            Contract contract = employee.getContracts().stream()
//                    .filter(c -> c.getId() == contractId)
//                    .findFirst().orElseThrow(() -> new NoSuchElementException("No such contract found with id " + contractId +" for employee with id "+employeeId));
            ContractRepo contractRepo = (ContractRepo) new ContractRepo().withEntityManager(em);
            Contract contract = contractRepo.findOne(contractId).orElseThrow(() -> new NoSuchElementException("No such contract found with id " + contractId));
            if(contract.getEmployee().getId()!=employeeId)
                throw new IllegalArgumentException("Contract with id "+contractId+" does not belong to employee with id "+employeeId);
            return ContractMapper.INSTANCE.toDto(contract);
        });
    }

    public VacationDto getVacation(int employeeId, int vacationId) {
        return Database.doInTransaction(em -> {
            EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
            Employee employee = employeeRepo.findOne(employeeId).orElseThrow(()->
                    new NoSuchElementException("No such employee found with id "+employeeId));
            Vacation vacation = employee.getVacations().stream()
                    .filter(v -> v.getId() == vacationId)
                    .findFirst().orElseThrow(() -> new NoSuchElementException("No such vacation found with id " + vacationId +" for employee with id "+employeeId));
            return VacationMapper.INSTANCE.toDto(vacation);
        });
    }

}
