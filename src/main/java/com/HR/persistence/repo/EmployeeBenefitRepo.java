package com.HR.persistence.repo;

import com.HR.persistence.entities.EmployeeBenefit;
import jakarta.persistence.EntityManager;

public class EmployeeBenefitRepo extends RepoImpl<EmployeeBenefit>{
    public EmployeeBenefitRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
