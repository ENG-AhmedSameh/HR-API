package com.HR.persistence.repo;

import com.HR.persistence.entities.Contract;
import jakarta.persistence.EntityManager;

public class ContractRepo extends RepoImpl<Contract>{
    public ContractRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
