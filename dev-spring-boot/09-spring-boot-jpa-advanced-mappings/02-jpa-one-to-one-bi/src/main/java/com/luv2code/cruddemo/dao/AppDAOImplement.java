package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // ovo je da bi spring nasao komponentu tokom skeniranja
public class AppDAOImplement implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {

        // ovo ce da vrati i instructor details jer je default ponasanje @OneToOne
        // postavljeno na eager
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);

        // ovo ce onda da obrise i instructor details objekat jer je cascade postavljen na ALL
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        // ovo sad vraca instructorDetail ali sa tim vraca i odgovarajuceg instructor-a
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        // uklanjam povezan objekat
        // prekidam bidirection vezu
        instructorDetail.getInstructor().setInstructorDetail(null);

        // sa ovim ce da se obrise i odgovarajuci instructor
        entityManager.remove(instructorDetail);
    }
}
