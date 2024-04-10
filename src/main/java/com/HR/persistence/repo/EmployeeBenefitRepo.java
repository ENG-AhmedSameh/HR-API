package com.HR.persistence.repo;

import com.HR.persistence.entities.EmployeeBenefit;
import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class EmployeeBenefitRepo extends RepoImpl<EmployeeBenefit>{
    public EmployeeBenefitRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<EmployeeBenefit> find(EmployeebenefitId employeebenefitId){
        return Optional.ofNullable(entityManager.find(EmployeeBenefit.class, employeebenefitId));
    }
}
