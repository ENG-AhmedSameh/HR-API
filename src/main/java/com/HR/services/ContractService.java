package com.HR.services;

import com.HR.dto.ContractDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Contract;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.repo.ContractRepo;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.util.mappers.ContractMapper;

import java.util.NoSuchElementException;

public class ContractService extends GenericResourceService<ContractDto, Contract, ContractRepo> {

    public ContractService() {
        super(new ContractRepo(), ContractMapper.INSTANCE);
    }

    @Override
    public ContractDto update(ContractDto contractDto, int contractId) {
        return Database.doInTransaction(em -> {
            ContractRepo contractRepo = (ContractRepo) new ContractRepo().withEntityManager(em);
            Contract contractEntity = contractRepo.findOne(contractId)
                    .orElseThrow(() -> new NoSuchElementException("No such contract found with id " + contractId));

            boolean hasChanges = false;

            // Update employee reference
            if (contractDto.getEmployeeID() != null && !contractDto.getEmployeeID().equals(contractEntity.getEmployee().getId())) {
                EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
                Employee employee = employeeRepo.findOne(contractDto.getEmployeeID())
                        .orElseThrow(() -> new NoSuchElementException("No such employee found with id " + contractDto.getEmployeeID()));
                contractEntity.setEmployee(employee);
                hasChanges = true;
            }

            // Update other fields if changes are detected
            if (contractDto.getContractType() != null && !contractDto.getContractType().equals(contractEntity.getContractType())) {
                contractEntity.setContractType(contractDto.getContractType());
                hasChanges = true;
            }
            if (contractDto.getStartDate() != null && !contractDto.getStartDate().equals(contractEntity.getStartDate())) {
                contractEntity.setStartDate(contractDto.getStartDate());
                hasChanges = true;
            }
            if (contractDto.getEndDate() != null && !contractDto.getEndDate().equals(contractEntity.getEndDate())) {
                contractEntity.setEndDate(contractDto.getEndDate());
                hasChanges = true;
            }
            if (contractDto.getSalary() != null && !contractDto.getSalary().equals(contractEntity.getSalary())) {
                contractEntity.setSalary(contractDto.getSalary());
                hasChanges = true;
            }
            if (contractDto.getHoursPerWeek() != null && !contractDto.getHoursPerWeek().equals(contractEntity.getHoursPerWeek())) {
                contractEntity.setHoursPerWeek(contractDto.getHoursPerWeek());
                hasChanges = true;
            }
            if (contractDto.getTerms() != null && !contractDto.getTerms().equals(contractEntity.getTerms())) {
                contractEntity.setTerms(contractDto.getTerms());
                hasChanges = true;
            }

            // Persist changes if there are any
            if (hasChanges) {
                contractRepo.update(contractEntity);
            } else {
                throw new IllegalArgumentException("No changes found in the request body");
            }

            // Use MapStruct for mapping to DTO
            return ContractMapper.INSTANCE.toDto(contractEntity);
        });
    }

}
