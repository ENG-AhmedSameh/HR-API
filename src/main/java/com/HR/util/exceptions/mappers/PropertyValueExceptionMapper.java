package com.HR.util.exceptions.mappers;

import com.HR.util.exceptions.response.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.PropertyValueException;

@Provider
public class PropertyValueExceptionMapper implements ExceptionMapper<PropertyValueException> {
    @Override
    public Response toResponse(PropertyValueException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Bad Request")
                .message("An error occurred while processing your request. Please ensure all required properties are set.")
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .build();
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}