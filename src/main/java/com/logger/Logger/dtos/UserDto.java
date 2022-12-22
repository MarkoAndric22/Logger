package com.logger.Logger.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class UserDto {

    private String username;
    private String email;
    private int logCount;

    public UserDto(String username, String email, int logCount) {
        this.username = username;
        this.email = email;
        this.logCount = logCount;
    }

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", logCount=" + logCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return logCount == userDto.logCount && Objects.equals(username, userDto.username) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, logCount);
    }
}
