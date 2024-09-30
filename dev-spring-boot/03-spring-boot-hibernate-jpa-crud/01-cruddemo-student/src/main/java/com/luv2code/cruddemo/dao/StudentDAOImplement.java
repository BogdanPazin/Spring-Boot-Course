package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImplement implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = entityManager.createQuery("FROM Student", Student.class);

        List<Student> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student WHERE firstName=:theData", Student.class);

        typedQuery.setParameter("theData", firstName);

        List<Student> list = typedQuery.getResultList();

        return list;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Student stud = entityManager.find(Student.class, id);

        entityManager.remove(stud);
    }

    @Override
    @Transactional
    public void deleteAllStudents() {
        int rowsAffected = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        System.out.println("Number of rows deleted: " + rowsAffected);
    }
}
