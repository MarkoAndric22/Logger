package com.logger.Logger.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class LogDto {
    private String message;
    @NotNull(message = "Log type is required!")
    private Integer logType;
    @Column(name = "created_date", columnDefinition = "date not null default CURRENT_DATE")
    private Date createdDate;

    public LogDto(String message, Integer logType, Date createdDate) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
    }

    public LogDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public String toString() {
        return "LogDto{" +
                "message='" + message + '\'' +
                ", logType=" + logType +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDto logDto = (LogDto) o;
        return Objects.equals(message, logDto.message) && Objects.equals(logType, logDto.logType) && Objects.equals(createdDate, logDto.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, logType, createdDate);
    }
}
