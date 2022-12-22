package com.logger.Logger.exceptions;

public class NoContentException extends Exception{

    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public NoContentException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public NoContentException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
