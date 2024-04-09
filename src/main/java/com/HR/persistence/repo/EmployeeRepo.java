package com.HR.persistence.repo;

import com.HR.persistence.entities.Employee;
import jakarta.persistence.EntityManager;

public class EmployeeRepo extends RepoImpl<Employee>{
    public EmployeeRepo(EntityManager entityManager) {
        super(entityManager);
    }

}
