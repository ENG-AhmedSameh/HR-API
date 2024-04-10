package com.HR.controllers.rest;

import com.HR.dto.TeamDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TeamControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/teams";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetAllTeams() {
        //Arrange
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        //Act
        Response response = request.get();

        //Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetTeam() {
        int teamId = 1;
        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", teamId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        TeamDto team = response.readEntity(TeamDto.class);
        assertEquals(teamId, team.getId());
    }

    @Test
    public void testCreateResource() {
        TeamDto newTeam = new TeamDto();
        newTeam.setTeamName("New Dream Team");
        newTeam.setDepartmentID(2);
        newTeam.setTeamLeaderID(3);

        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.post(Entity.entity(newTeam, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        TeamDto createdTeam = response.readEntity(TeamDto.class);
        assertNotNull(createdTeam);
        assertTrue(createdTeam.getId() != null && createdTeam.getId() > 0);
        if (createdTeam.getId() != null) {
            Response deleteResponse = client.target(BASE_URL + "/" + createdTeam.getId())
                    .request()
                    .delete();
            assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus(), "Failed to delete the created team.");
        }
    }

    @Test
    public void testUpdateTeam() {
        int teamId = 1; // Assuming this ID exists and is valid

        // Arrange
        WebTarget getTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", teamId);
        TeamDto originalTeam = getTarget.request(MediaType.APPLICATION_JSON).get(TeamDto.class);

        // Act
        TeamDto updatedTeam = new TeamDto();
        updatedTeam.setTeamName("Updated Dream Team");
        updatedTeam.setDepartmentID(2); // Assuming a new valid department ID
        updatedTeam.setTeamLeaderID(4); // Assuming a new valid team leader ID

        WebTarget updateTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", teamId);
        Response updateResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedTeam, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), updateResponse.getStatus());
        TeamDto teamResponse = updateResponse.readEntity(TeamDto.class);
        assertEquals(teamId, teamResponse.getId());
        assertEquals("Updated Dream Team", teamResponse.getTeamName());

        // Restore the original state
        Response restoreResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(originalTeam, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.OK.getStatusCode(), restoreResponse.getStatus(), "Failed to restore the original team state.");
    }


    @Test
    public void testDeleteTeam() {
        // Step 1: Insert an entity (team) into the database
        TeamDto newTeam = new TeamDto();
        newTeam.setTeamName("Temporary Dream Team");
        newTeam.setDepartmentID(1); // Assuming a valid department ID
        newTeam.setTeamLeaderID(1); // Assuming a valid team leader ID

        WebTarget createTarget = client.target(BASE_URL);
        Response createResponse = createTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newTeam, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), createResponse.getStatus());

        TeamDto createdTeam = createResponse.readEntity(TeamDto.class);
        int teamId = createdTeam.getId(); // Assuming getId() fetches the ID

        // Step 2: Attempt to delete the inserted entity (team)
        WebTarget deleteTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", teamId);
        Response deleteResponse = deleteTarget.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus(), "Failed to delete the created team.");

        // Optional: Verify the entity no longer exists
        Response getResponse = deleteTarget.request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), getResponse.getStatus(), "Team was not successfully deleted.");
    }

}

