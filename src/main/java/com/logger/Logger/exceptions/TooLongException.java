package com.logger.Logger.exceptions;

public class TooLongException extends Exception{

    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public TooLongException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public TooLongException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
