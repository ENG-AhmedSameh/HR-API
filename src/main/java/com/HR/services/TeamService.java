package com.HR.services;

import com.HR.dto.TeamDto;
import com.HR.persistence.Database;
import com.HR.persistence.entities.Department;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.Team;
import com.HR.persistence.repo.DepartmentRepo;
import com.HR.persistence.repo.EmployeeRepo;
import com.HR.persistence.repo.TeamRepo;
import com.HR.util.mappers.TeamMapper;

import java.util.NoSuchElementException;

public class TeamService extends GenericResourceService<TeamDto, Team, TeamRepo>{
    public TeamService() {
        super(new TeamRepo(), TeamMapper.INSTANCE);
    }

    @Override
    public TeamDto update(TeamDto teamDto, int teamId) {
        return Database.doInTransaction(em -> {
            TeamRepo teamRepo = (TeamRepo) new TeamRepo().withEntityManager(em);
            Team teamEntity = teamRepo.findOne(teamId)
                    .orElseThrow(() -> new NoSuchElementException("No such team found with id " + teamId));

            boolean hasChanges = false;

            // Update team name if it has changed
            if (teamDto.getTeamName() != null && !teamDto.getTeamName().equals(teamEntity.getTeamName())) {
                teamEntity.setTeamName(teamDto.getTeamName());
                hasChanges = true;
            }

            // Update department if it has changed
            if (teamDto.getDepartmentID() != null && !teamDto.getDepartmentID().equals(teamEntity.getDepartment().getId())) {
                DepartmentRepo departmentRepo = (DepartmentRepo) new DepartmentRepo().withEntityManager(em);
                Department department = departmentRepo.findOne(teamDto.getDepartmentID())
                        .orElseThrow(() -> new NoSuchElementException("No such department found with id " + teamDto.getDepartmentID()));
                teamEntity.setDepartment(department);
                hasChanges = true;
            }

            // Update team leader if it has changed
            if (teamDto.getTeamLeaderID() != null && (teamEntity.getTeamLeader() == null || !teamDto.getTeamLeaderID().equals(teamEntity.getTeamLeader().getId()))) {
                EmployeeRepo employeeRepo = (EmployeeRepo) new EmployeeRepo().withEntityManager(em);
                Employee teamLeader = employeeRepo.findOne(teamDto.getTeamLeaderID())
                        .orElseThrow(() -> new NoSuchElementException("No such employee found with id " + teamDto.getTeamLeaderID()));
                teamEntity.setTeamLeader(teamLeader);
                hasChanges = true;
            }

            // Persist changes if any
            if (hasChanges) {
                teamRepo.update(teamEntity);
            } else {
                throw new IllegalArgumentException("No changes found in the request body");
            }

            // Map entity to DTO and return
            return TeamMapper.INSTANCE.toDto(teamEntity);
        });
    }

}
