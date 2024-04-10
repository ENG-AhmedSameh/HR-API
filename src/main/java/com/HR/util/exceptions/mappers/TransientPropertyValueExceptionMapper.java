package com.HR.util.exceptions.mappers;

import com.HR.util.exceptions.response.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.TransientPropertyValueException;

@Provider
public class TransientPropertyValueExceptionMapper implements ExceptionMapper<TransientPropertyValueException> {
    @Override
    public Response toResponse(TransientPropertyValueException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Bad Request")
                .message("An error occurred while processing your request. Please ensure all referenced objects are saved before saving this object.")
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .build();
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}