package com.HR.services;

import com.HR.dto.VacationDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.Vacation;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.persistence.repo.VacationRepo;
import com.HR.util.mappers.VacationMapper;

import java.util.NoSuchElementException;

public class VacationService extends GenericResourceService<VacationDto, Vacation, VacationRepo>{
    public VacationService() {
        super(new VacationRepo(), VacationMapper.INSTANCE);
    }

    @Override
    public VacationDto update(VacationDto vacationDto, int vacationId) {
        return Database.doInTransaction(em -> {
            VacationRepo vacationRepo = (VacationRepo) new VacationRepo().withEntityManager(em);
            Vacation vacationEntity = vacationRepo.findOne(vacationId)
                    .orElseThrow(() -> new NoSuchElementException("No such vacation found with id " + vacationId));

            boolean hasChanges = false;

            // Update employee if it has changed
            if (vacationDto.getEmployeeID() != null && !vacationDto.getEmployeeID().equals(vacationEntity.getEmployee().getId())) {
                EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
                Employee employee = employeeRepo.findOne(vacationDto.getEmployeeID())
                        .orElseThrow(() -> new NoSuchElementException("No such employee found with id " + vacationDto.getEmployeeID()));
                vacationEntity.setEmployee(employee);
                hasChanges = true;
            }

            // Update start date if it has changed
            if (vacationDto.getStartDate() != null && !vacationDto.getStartDate().equals(vacationEntity.getStartDate())) {
                vacationEntity.setStartDate(vacationDto.getStartDate());
                hasChanges = true;
            }

            // Update end date if it has changed
            if (vacationDto.getEndDate() != null && !vacationDto.getEndDate().equals(vacationEntity.getEndDate())) {
                vacationEntity.setEndDate(vacationDto.getEndDate());
                hasChanges = true;
            }

            // Update status if it has changed
            if (vacationDto.getStatus() != null && !vacationDto.getStatus().equals(vacationEntity.getStatus())) {
                vacationEntity.setStatus(vacationDto.getStatus());
                hasChanges = true;
            }

            // Persist changes if any
            if (hasChanges) {
                vacationRepo.update(vacationEntity);
            } else {
                throw new IllegalArgumentException("No changes found in the request body");
            }

            // Map entity to DTO and return
            return VacationMapper.INSTANCE.toDto(vacationEntity);
        });
    }

}
