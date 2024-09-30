package com.luv2code.springboot.thymeleafdemo.model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String language;
    private List<String> systems;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getSystems() {
        return systems;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSystems(List<String> systems) {
        this.systems = systems;
    }
}
