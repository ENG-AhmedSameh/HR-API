package com.HR.controllers.rest;

import com.HR.dto.DepartmentDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/departments";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetAllDepartments() {
        //Arrange
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        //Act
        Response response = request.get();

        //Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetDepartment() {
        int departmentId = 1; // Assuming this department ID exists
        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", departmentId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        DepartmentDto department = response.readEntity(DepartmentDto.class);
        assertEquals(departmentId, department.getId());
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDto newDepartment = new DepartmentDto();
        newDepartment.setDepartmentName("Research and Development");
        newDepartment.setManagerID(2); // Assuming an existing manager ID

        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.post(Entity.entity(newDepartment, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        DepartmentDto createdDepartment = response.readEntity(DepartmentDto.class);
        assertNotNull(createdDepartment);
        assertTrue(createdDepartment.getId() != null && createdDepartment.getId() > 0);
        // Cleanup
        if (createdDepartment.getId() != null) {
            client.target(BASE_URL + "/" + createdDepartment.getId()).request().delete();
        }
    }

    @Test
    public void testUpdateDepartment() {
        int departmentId = 1; // Assuming this ID exists and is valid

        // Arrange
        WebTarget getTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", departmentId);
        DepartmentDto originalDepartment = getTarget.request(MediaType.APPLICATION_JSON).get(DepartmentDto.class);

        // Act
        DepartmentDto updatedDepartment = new DepartmentDto();
        String currentTimeStamp = String.valueOf(LocalDateTime.now());
        updatedDepartment.setDepartmentName("Innovation and Development: "+ currentTimeStamp);
        updatedDepartment.setManagerID(3); // Assuming a new valid manager ID

        WebTarget updateTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", departmentId);
        Response updateResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedDepartment, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), updateResponse.getStatus());
        DepartmentDto departmentResponse = updateResponse.readEntity(DepartmentDto.class);
        assertEquals(departmentId, departmentResponse.getId());
        assertEquals("Innovation and Development: "+ currentTimeStamp, departmentResponse.getDepartmentName());

        // Restore the original state
        Response restoreResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(originalDepartment, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.OK.getStatusCode(), restoreResponse.getStatus(), "Failed to restore the original department state.");
    }

    @Test
    public void testDeleteDepartment() {
        // Create a department for testing deletion
        DepartmentDto newDepartment = new DepartmentDto();
        newDepartment.setDepartmentName("Temporary Department");
        newDepartment.setManagerID(1); // Assuming an existing manager ID

        WebTarget createTarget = client.target(BASE_URL);
        Response createResponse = createTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newDepartment, MediaType.APPLICATION_JSON));

        DepartmentDto createdDepartment = createResponse.readEntity(DepartmentDto.class);
        int departmentId = createdDepartment.getId(); // Assuming getId() fetches the ID

        // Now delete the department
        WebTarget deleteTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", departmentId);
        Response deleteResponse = deleteTarget.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());

        // Verify the department no longer exists
        Response getResponse = deleteTarget.request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), getResponse.getStatus(), "Department was not successfully deleted.");
    }

}

