package com.logger.Logger.exceptions;

public class InvalidParamException extends Exception{

    private static final long serialVersionUID = -1664438367194701485L;

    private Object entity;

    public InvalidParamException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
