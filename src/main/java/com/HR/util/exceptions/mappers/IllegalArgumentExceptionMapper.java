package com.HR.util.exceptions.mappers;

import com.HR.util.exceptions.response.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        String path = uriInfo.getRequestUri().toString();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Bad Request")
                .message(exception.getMessage())
                .path(path)
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .timestamp(LocalDateTime.now())
                .build();

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}