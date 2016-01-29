package uk.co.littlemike.bitshadow.web.exceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(400)
                .entity(new ExceptionRepresentation(exception))
                .build();
    }
}