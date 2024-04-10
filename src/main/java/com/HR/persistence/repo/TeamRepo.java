package com.HR.persistence.repo;

import com.HR.persistence.entities.Team;
import jakarta.persistence.EntityManager;

public class TeamRepo extends RepoImpl<Team>{
    public TeamRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
