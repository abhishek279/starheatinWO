package com.starheatingWO.starheatingWO.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private boolean exported = false;

    // Constructors, getters, and setters
    public Timesheet() {
    }

    public Timesheet(String name, LocalDateTime loginTime, LocalDateTime logoutTime) {
        this.name = name;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }
}