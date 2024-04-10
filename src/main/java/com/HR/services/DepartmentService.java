package com.HR.services;

import com.HR.dto.DepartmentDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Department;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.repo.DepartmentRepo;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.util.mappers.DepartmentMapper;

import java.util.NoSuchElementException;

public class DepartmentService extends GenericResourceService<DepartmentDto, Department, DepartmentRepo>{

    public DepartmentService() {
        super(new DepartmentRepo(), DepartmentMapper.INSTANCE);
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto, int departmentId) {
        return Database.doInTransaction(em -> {
            DepartmentRepo departmentRepo = (DepartmentRepo) new DepartmentRepo().withEntityManager(em);
            Department departmentEntity = departmentRepo.findOne(departmentId)
                    .orElseThrow(() -> new NoSuchElementException("No such department found with id " + departmentId));

            boolean hasChanges = false;

            // Update department name
            if (departmentDto.getDepartmentName() != null && !departmentDto.getDepartmentName().equals(departmentEntity.getDepartmentName())) {
                departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
                hasChanges = true;
            }

            // Update manager if the ID is different and not null
            if (departmentDto.getManagerID() != null && (departmentEntity.getManager() == null || !departmentDto.getManagerID().equals(departmentEntity.getManager().getId()))) {
                EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
                Employee manager = employeeRepo.findOne(departmentDto.getManagerID())
                        .orElseThrow(() -> new NoSuchElementException("No such employee found with id " + departmentDto.getManagerID()));
                departmentEntity.setManager(manager);
                hasChanges = true;
            }

            // Persist changes if any
            if (hasChanges) {
                departmentRepo.update(departmentEntity);
            } else {
                throw new IllegalArgumentException("No changes found in the request body");
            }

            // Use MapStruct for mapping to DTO
            return DepartmentMapper.INSTANCE.toDto(departmentEntity);
        });
    }

}
