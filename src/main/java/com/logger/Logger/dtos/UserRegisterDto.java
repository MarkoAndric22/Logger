package com.logger.Logger.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserRegisterDto {

    @NotNull(message = "Username is required!")
    @Size(min = 3, message = " Username must have min 3 characters")
    @Column(unique = true, nullable = false)
    private String username;
    @NotNull(message = "Password is required!")
    @Size(min = 8, message = " Password must have min 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$")
    private String password;
    @NotNull(message = "Email is required!")
    @Pattern(regexp ="^(.+)@(.+)$")
    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(@NotNull(message = "Username is required!")
                            @Size(min = 3, message = " Username must have min 3 characters") String username,
                           @NotNull(message = "Password is required!")
                            @Size(min = 8, message = " Password must have min 8 characters")
                            @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$") String password,
                           @NotNull(message = "Email is required!")
                           @Pattern(regexp ="^(.+)@(.+)$") String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserRegisterDto(@NotNull(message = "Username is required!")
                           @Size(min = 3, message = " Username must have min 3 characters") String username,
                           @NotNull(message = "Password is required!")
                           @Size(min = 8, message = " Password must have min 8 characters")
                           @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$") String password,
                           @NotNull(message = "Email is required!")
                           @Pattern(regexp ="^(.+)@(.+)$") String email,
                           String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterDto that = (UserRegisterDto) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, firstName, lastName);
    }
}
