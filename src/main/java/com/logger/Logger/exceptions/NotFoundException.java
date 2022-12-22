package com.logger.Logger.exceptions;

public class NotFoundException extends Exception{

    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public NotFoundException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
