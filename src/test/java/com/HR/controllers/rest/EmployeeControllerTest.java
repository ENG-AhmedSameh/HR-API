package com.HR.controllers.rest;

import com.HR.dto.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/employees";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetManagedEmployees() {
        int employeeId = 1; // Assuming this employee ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/managedEmployees").resolveTemplate("id", employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetEmployeeBenefits() {
        int employeeId = 1; // Assuming this employee ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/benefits").resolveTemplate("id", employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    @Test
    public void testGetLeadedTeams() {
        int employeeId = 1; // Assuming this employee ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/leadedTeams").resolveTemplate("id", employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetContracts() {
        int employeeId = 1; // Assuming this employee ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/contracts").resolveTemplate("id", employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetContract() {
        int employeeId = 1; // Assuming this employee ID exists
        int contractId = 1; // Assuming this contract ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/contracts/{contractId}")
                .resolveTemplate("id", employeeId)
                .resolveTemplate("contractId", contractId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacations() {
        int employeeId = 1; // Assuming this employee ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/vacations").resolveTemplate("id", employeeId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVacation() {
        int employeeId = 1; // Assuming this employee ID exists
        int vacationId = 1; // Assuming this vacation ID exists
        WebTarget target = client.target(BASE_URL + "/{id}/vacations/{vacationId}")
                .resolveTemplate("id", employeeId)
                .resolveTemplate("vacationId", vacationId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}