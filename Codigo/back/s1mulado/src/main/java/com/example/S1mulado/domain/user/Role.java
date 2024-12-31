package com.example.S1mulado.domain.user;

public enum Role {
    ADMIN("admin"),
    TEACHER("teacher"),
    STUDENT("student");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
