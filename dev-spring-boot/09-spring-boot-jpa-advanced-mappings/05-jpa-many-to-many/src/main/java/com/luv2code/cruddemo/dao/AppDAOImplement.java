package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = instructor.getCourses();

        for(Course course : courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> typedQuery = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
                
        typedQuery.setParameter("data", theId);

        List<Course> courses = typedQuery.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> typedQuery = entityManager.createQuery(
                "select instructor from Instructor instructor " +
                "JOIN FETCH instructor.courses " +
                "JOIN FETCH instructor.instructorDetail " + 
                        "where instructor.id = :data", Instructor.class);

        typedQuery.setParameter("data", theId);

        Instructor resInstructor = typedQuery.getSingleResult();

        return resInstructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = entityManager.find(Course.class, theId);

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseWithReviewsByCourseId(int theId) {
        TypedQuery<Course> typedQuery = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class);

        typedQuery.setParameter("data", theId);

        Course course = typedQuery.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseWithStudentsByCourseId(int theId) {
        TypedQuery<Course> typedQuery = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        typedQuery.setParameter("data", theId);

        Course course = typedQuery.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentWithCoursesByStudentId(int theId) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);

        typedQuery.setParameter("data", theId);

        Student student = typedQuery.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = entityManager.find(Student.class, theId);

        entityManager.remove(student);
    }
}
