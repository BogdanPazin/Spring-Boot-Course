package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// ova anotacija je podanotacija od component anotacije
// omogucava skeniranje komponenti
@Repository
public class StudentDAOImplement implements StudentDAO{

    // svaki DAO mora da ima entity manager koji ce da vrsi kontakt sa bazom
    private EntityManager entityManager;

    // radim constructor injection za entity manager-a
    @Autowired
    public StudentDAOImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // ova anotacija se koristi kada se azurira baza podataka
    public void save(Student student) {
        entityManager.persist(student); //sa ovim se cuva novi student u bazi
    }

    @Override
    public Student findById(int id) {
                                // prvi parametar je entity klasa a drugi je primary key
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
                                            //u query-jevima se vrednosti odnose na samu entity klasu
        TypedQuery<Student> typedQuery = entityManager.createQuery("FROM Student", Student.class);

        List<Student> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student WHERE firstName=:theData", Student.class);
                    // u samom query-ju se takav parametar pise sa :

        // ovim sam podesio koja vrednost da se trazi u bazi
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