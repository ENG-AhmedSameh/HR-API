package com.HR.persistence.repo;

import com.HR.persistence.entities.Benefit;
import jakarta.persistence.EntityManager;

public class BenefitRepo extends RepoImpl<Benefit>{
    public BenefitRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
