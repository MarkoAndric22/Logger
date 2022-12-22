package com.logger.Logger.exceptions;

public class EntityExistsException extends Exception{
    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public EntityExistsException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public EntityExistsException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
