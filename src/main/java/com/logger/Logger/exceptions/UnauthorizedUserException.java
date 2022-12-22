package com.logger.Logger.exceptions;

public class UnauthorizedUserException extends Exception{

    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public UnauthorizedUserException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
