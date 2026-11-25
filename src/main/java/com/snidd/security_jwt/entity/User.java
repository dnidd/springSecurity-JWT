package com.snidd.security_jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * User entity.
 * Annotation @Entity tells JPA that this class User
 * represents DB table user with JPA naming convention.
 * Hibernate connect entity to table.
 */
@Entity
public class User {

    @Id
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
