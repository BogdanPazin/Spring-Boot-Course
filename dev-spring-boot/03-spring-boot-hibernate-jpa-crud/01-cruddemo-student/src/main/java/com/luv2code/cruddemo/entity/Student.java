package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

// sa entity anotacijom naglasavam da ce ova klasa da se mapira sa nekom tabelom u bazi
@Entity
@Table(name="student") // sa ovom anotacijom eksplicitno kazem sa kojom tabelom se povezuje
public class Student {

    // posto znam da je id primarni kljuc u tabeli, onda to naglasavam i ovde sa anotacijom id
    // takodje mora i da se naglasi kako se primarni kljuc generise, posto je stavljen kao auto_increment onda se koristi identity
    // sto znaci da ce mysql da se bavi tim inkrementima
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;


    // moram da imam prazan konstruktor
    public Student() {

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + getId() + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
