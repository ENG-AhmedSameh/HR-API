package com.HR.controllers.rest;

import com.HR.dto.TeamDto;
import com.HR.services.TeamService;
import jakarta.ws.rs.Path;

@Path("teams")
public class TeamController extends ResourceController<TeamDto, TeamService>{
    public TeamController() {
        super(new TeamService());
    }
}
