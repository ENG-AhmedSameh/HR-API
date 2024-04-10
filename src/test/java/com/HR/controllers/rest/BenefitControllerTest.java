package com.HR.controllers.rest;


import com.HR.dto.BenefitDto;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BenefitControllerTest {

    private final String BASE_URL = "http://localhost:9090/HR-API/webapi/benefits";
    private Client client = ClientBuilder.newClient();

    @Test
    public void testGetAllBenefits() {
        //Arrange
        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        //Act
        Response response = request.get();

        //Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetBenefit() {
        int benefitId = 1; // Assuming this benefit ID exists
        WebTarget target = client.target(BASE_URL + "/{id}").resolveTemplate("id", benefitId);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        BenefitDto benefit = response.readEntity(BenefitDto.class);
        assertEquals(benefitId, benefit.getId());
    }

    @Test
    public void testCreateBenefit() {
        BenefitDto newBenefit = new BenefitDto();
        newBenefit.setBenefitName("Health Insurance");
        newBenefit.setDescription("Comprehensive health insurance plan.");

        WebTarget target = client.target(BASE_URL);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);

        Response response = request.post(Entity.entity(newBenefit, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        BenefitDto createdBenefit = response.readEntity(BenefitDto.class);
        assertNotNull(createdBenefit);
        assertTrue(createdBenefit.getId() != null && createdBenefit.getId() > 0);
        // Cleanup
        if (createdBenefit.getId() != null) {
            client.target(BASE_URL + "/" + createdBenefit.getId()).request().delete();
        }
    }

    @Test
    public void testUpdateBenefit() {
        int benefitId = 1; // Assuming this ID exists and is valid

        // Arrange
        WebTarget getTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", benefitId);
        BenefitDto originalBenefit = getTarget.request(MediaType.APPLICATION_JSON).get(BenefitDto.class);

        // Act
        BenefitDto updatedBenefit = new BenefitDto();
        String currentTimeStamp = String.valueOf(LocalDateTime.now());
        updatedBenefit.setBenefitName("Updated Health Insurance: "+ currentTimeStamp);
        updatedBenefit.setDescription("Updated comprehensive health insurance plan.");

        WebTarget updateTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", benefitId);
        Response updateResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedBenefit, MediaType.APPLICATION_JSON));

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), updateResponse.getStatus());
        BenefitDto benefitResponse = updateResponse.readEntity(BenefitDto.class);
        assertEquals(benefitId, benefitResponse.getId());
        assertEquals("Updated Health Insurance: "+ currentTimeStamp, benefitResponse.getBenefitName());

        // Restore the original state
        Response restoreResponse = updateTarget.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(originalBenefit, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.OK.getStatusCode(), restoreResponse.getStatus(), "Failed to restore the original benefit state.");
    }

    @Test
    public void testDeleteBenefit() {
        // Create a benefit for testing deletion
        BenefitDto newBenefit = new BenefitDto();
        newBenefit.setBenefitName("Temporary Benefit");
        newBenefit.setDescription("This is a temporary benefit.");

        WebTarget createTarget = client.target(BASE_URL);
        Response createResponse = createTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(newBenefit, MediaType.APPLICATION_JSON));

        BenefitDto createdBenefit = createResponse.readEntity(BenefitDto.class);
        int benefitId = createdBenefit.getId(); // Assuming getId() fetches the ID

        // Now delete the benefit
        WebTarget deleteTarget = client.target(BASE_URL + "/{id}").resolveTemplate("id", benefitId);
        Response deleteResponse = deleteTarget.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());

        // Verify the benefit no longer exists
        Response getResponse = deleteTarget.request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), getResponse.getStatus(), "Benefit was not successfully deleted.");
    }

}

