package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    //podesio sam cascade tako da ako obrisem course, ne brisem instruktora koji je bio vezan za taj kurs
    //vise kurseva moze biti povezano sa jednim instruktorom
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    // ovo je naziv kolone u samoj tabeli course, kako bi course pronasao tacnog instruktora
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL, // SA OVIM CE SE OBRISATI I SVE RECENZIJE SA KURSOM, AKO OBRISEM KURS
            fetch = FetchType.LAZY) // SA OVIM CE SE RECENZIJE UCITATI PO ZAHTEVU A NE AUTOMATSKI

    // ZBOG TOGA STO JE UNIDIRECTIONAL ASOCIJACIJA, U KLASI course MORA DA SE OZNACI
    // DA HIBERNATE ZNA DA PRONADJE ODGOVARAJUCE RECENZIJE ZA TRAZENI KURS
    @JoinColumn(name = "course_id")
    private List<Review> reviews = new ArrayList<>();

    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
