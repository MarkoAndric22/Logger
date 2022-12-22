package com.logger.Logger.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @NotNull(message = "Username is required!")
    @Size(min = 3, message = " Username must have min 3 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = " Password must have min 3 characters")
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$")
    private String password;
    @NotNull(message = "Email is required!")
    @Pattern(regexp ="^(.+)@(.+)$")
    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32) default 'ROLE_CLIENT'")
    private UserEnum userRole = UserEnum.ROLE_CLIENT;
    private int logCount;

    public User() {
    }

    public User( String username, String password, String email, String firstName, String lastName, UserEnum userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
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


    public UserEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserEnum userRole) {
        this.userRole = userRole;
    }

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, firstName, lastName, userRole);
    }
}
