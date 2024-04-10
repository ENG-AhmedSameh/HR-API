package com.HR.controllers.soap;

import com.HR.dto.TeamDto;
import com.HR.services.TeamService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class TeamWebService {

    private final TeamService service = new TeamService();

    @WebMethod
    public List<TeamDto> getTeams(
            @WebParam(name = "page") int page,
            @WebParam(name = "size") int size) {
        return service.getAll(page, size);
    }

    @WebMethod
    public TeamDto getTeam(@WebParam(name = "id") int id) {
        return service.get(id);
    }

    @WebMethod
    public TeamDto createTeam(@WebParam(name = "teamDto") TeamDto teamDto) {
        return service.create(teamDto);
    }

    @WebMethod
    public TeamDto updateTeam(
            @WebParam(name = "teamDto") TeamDto teamDto) {
        return service.update(teamDto, teamDto.getId()==null?0:teamDto.getId());
    }

    @WebMethod
    public void deleteTeam(@WebParam(name = "id") int id) {
        service.delete(id);
    }
}
