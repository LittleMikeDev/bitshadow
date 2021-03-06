package uk.co.littlemike.bitshadow.web.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionRepresentation {

    private final String message;
    private final String type;
    private final String[] stackFrames;

    public ExceptionRepresentation(Exception exception) {
        message = exception.getMessage();
        type = exception.getClass().getName();
        stackFrames = ExceptionUtils.getStackFrames(exception);
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public String[] getStackTrace() {
        return stackFrames;
    }
}
