package com.HR.controllers.rest;

import com.HR.dto.ContractDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContractControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/contracts";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetAllContracts() {
        //Arrange
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        //Act
        Response response = request.get();

        //Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetContract() {
        int contractId = 1; // Assuming this contract ID exists
        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", contractId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        ContractDto contract = response.readEntity(ContractDto.class);
        assertEquals(contractId, contract.getId());
    }

    @Test
    public void testCreateContract() {
        ContractDto newContract = new ContractDto();
        newContract.setEmployeeID(1); // Assuming an existing employee ID
        newContract.setContractType("Full-Time");
        newContract.setStartDate(LocalDate.of(2024, 1, 1));
        newContract.setEndDate(LocalDate.of(2025, 12, 31));
        newContract.setSalary(new BigDecimal("50000"));
        newContract.setHoursPerWeek(40);
        newContract.setTerms("Standard employment terms.");

        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.post(Entity.entity(newContract, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        ContractDto createdContract = response.readEntity(ContractDto.class);
        assertNotNull(createdContract);
        assertTrue(createdContract.getId() != null && createdContract.getId() > 0);
        // Cleanup
        if (createdContract.getId() != null) {
            client.target(BASE_URL + "/" + createdContract.getId()).request().delete();
        }
    }

    @Test
    public void testUpdateContract() {
        int contractId = 1; // Assuming this ID exists and is valid
        ContractDto updatedContract = new ContractDto();
        updatedContract.setEmployeeID(1); // Assuming an existing employee ID
        updatedContract.setContractType("Part-Time");
        updatedContract.setStartDate(LocalDate.of(2024, 1, 1));
        updatedContract.setEndDate(LocalDate.of(2025, 12, 31));
        updatedContract.setSalary(new BigDecimal("30000"));
        updatedContract.setHoursPerWeek(20);
        updatedContract.setTerms("Updated employment terms.");

        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", contractId);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedContract, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        ContractDto contractResponse = response.readEntity(ContractDto.class);
        assertEquals(contractId, contractResponse.getId());
        assertEquals("Part-Time", contractResponse.getContractType());
    }

    @Test
    public void testDeleteContract() {
        // Create a contract for testing deletion
        ContractDto newContract = new ContractDto();
        newContract.setEmployeeID(2); // Assuming an existing employee ID
        newContract.setContractType("Temporary");
        newContract.setStartDate(LocalDate.now());
        newContract.setEndDate(LocalDate.now().plusYears(1));
        newContract.setSalary(new BigDecimal("40000"));
        newContract.setHoursPerWeek(30);
        newContract.setTerms("Temporary contract terms.");

        WebTarget createTarget = client.target(BASE_URL);
        Response createResponse = createTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newContract, MediaType.APPLICATION_JSON));

        ContractDto createdContract = createResponse.readEntity(ContractDto.class);
        int contractId = createdContract.getId(); // Assuming getId() fetches the ID

        // Now delete the contract
        WebTarget deleteTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", contractId);
        Response deleteResponse = deleteTarget.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());

        // Verify the contract no longer exists
        Response getResponse = deleteTarget.request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), getResponse.getStatus(), "Contract was not successfully deleted.");
    }

    // Additional tests as necessary...
}
