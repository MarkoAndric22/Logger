package com.logger.Logger.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;
    @NotNull(message = "Log type is required!")
    private Integer logType;
    @Column(name = "created_date", columnDefinition = "date not null default CURRENT_DATE")
    private Date createdDate = new Date();
    @NotNull(message = "Client is required!")
    @JoinColumn(name = "client")
    @Column(name = "client")
    private String clientUsername;

    public Log(Integer id, String message, Integer logType, Date createdDate, String clientUsername) {
        this.id = id;
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
        this.clientUsername = clientUsername;
    }

    public Log(String message, Integer logType, Date createdDate, String clientUsername) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
        this.clientUsername = clientUsername;
    }

    public Log(String message, Integer logType) {
        this.message = message;
        this.logType = logType;
    }

    public Log(Integer logType) {
        this.logType = logType;
    }

    public Log() {
    }

    public Log(String message, Integer logType, Date createdDate) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", createdDate=" + createdDate +
                ", clientUsername='" + clientUsername + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(id, log.id) && Objects.equals(message, log.message) && logType == log.logType && Objects.equals(createdDate, log.createdDate) && Objects.equals(clientUsername, log.clientUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, logType, createdDate, clientUsername);
    }
}
