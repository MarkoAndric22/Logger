package com.logger.Logger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LogEnum {

    EMERGENCY(0),
    ALERT(1),
    CRITICAL(2),
    ERROR(3),
    WARNING(4),
    NOTICE(5),
    INFORMATIONAL(6),
    DEBUG(7);


    private Integer value;

    private LogEnum(Integer value) {
        this.value = value;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

}
