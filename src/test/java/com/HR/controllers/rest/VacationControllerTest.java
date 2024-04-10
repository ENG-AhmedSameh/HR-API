package com.HR.controllers.rest;

import com.HR.dto.VacationDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VacationControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/vacations";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetAllVacations() {
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacation() {
        int vacationId = 1; // Assuming this vacation ID exists
        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", vacationId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        VacationDto vacation = response.readEntity(VacationDto.class);
        assertEquals(vacationId, vacation.getId());
    }

    @Test
    public void testCreateVacation() {
        VacationDto newVacation = new VacationDto();
        newVacation.setEmployeeID(1); // Assuming an existing employee ID
        newVacation.setStartDate(LocalDate.now());
        newVacation.setEndDate(LocalDate.now().plusDays(7));
        newVacation.setStatus("Approved");

        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.post(Entity.entity(newVacation, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        VacationDto createdVacation = response.readEntity(VacationDto.class);
        assertNotNull(createdVacation);
        assertTrue(createdVacation.getId() != null && createdVacation.getId() > 0);
        // Cleanup
        if (createdVacation.getId() != null) {
            client.target(BASE_URL + "/" + createdVacation.getId()).request().delete();
        }
    }

    @Test
    public void testUpdateVacation() {
        int vacationId = 1; // Assuming this ID exists and is valid
        VacationDto updatedVacation = new VacationDto();
        updatedVacation.setEmployeeID(1); // Assuming an existing employee ID
        updatedVacation.setStartDate(LocalDate.now());
        updatedVacation.setEndDate(LocalDate.now().plusDays(10));
        updatedVacation.setStatus("Updated");

        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", vacationId);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedVacation, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        VacationDto vacationResponse = response.readEntity(VacationDto.class);
        assertEquals(vacationId, vacationResponse.getId());
        assertEquals("Updated", vacationResponse.getStatus());
    }

    @Test
    public void testDeleteVacation() {
        // Create a vacation for testing deletion
        VacationDto newVacation = new VacationDto();
        newVacation.setEmployeeID(1); // Assuming an existing employee ID
        newVacation.setStartDate(LocalDate.now());
        newVacation.setEndDate(LocalDate.now().plusDays(7));
        newVacation.setStatus("Temporary");

        WebTarget createTarget = client.target(BASE_URL);
        Response createResponse = createTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newVacation, MediaType.APPLICATION_JSON));

        VacationDto createdVacation = createResponse.readEntity(VacationDto.class);
        int vacationId = createdVacation.getId(); // Assuming getId() fetches the ID

        // Now delete the vacation
        WebTarget deleteTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", vacationId);
        Response deleteResponse = deleteTarget.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());

        // Verify the vacation no longer exists
        Response getResponse = deleteTarget.request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), getResponse.getStatus(), "Vacation was not successfully deleted.");
    }
}
