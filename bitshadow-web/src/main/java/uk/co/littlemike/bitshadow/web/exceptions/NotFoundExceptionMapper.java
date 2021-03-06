package uk.co.littlemike.bitshadow.web.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.littlemike.bitshadow.common.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    private static final Logger LOG = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

    @Override
    public Response toResponse(NotFoundException exception) {
        LOG.debug("Not found", exception);
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ExceptionRepresentation(exception))
                .build();
    }
}
