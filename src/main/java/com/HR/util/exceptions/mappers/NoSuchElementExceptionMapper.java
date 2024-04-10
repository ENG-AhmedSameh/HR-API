package com.HR.util.exceptions.mappers;

import com.HR.util.exceptions.response.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException>{
    @Context
    private UriInfo uriInfo;
    @Override
    public Response toResponse(NoSuchElementException e) {
        String path = uriInfo.getRequestUri().toString();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Not Found")
                .message(e.getMessage())
                .path(path)
                .status(Response.Status.NOT_FOUND.getStatusCode())
                .timestamp(LocalDateTime.now())
                .build();

        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}