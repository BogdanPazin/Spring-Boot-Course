package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        List<Course> courses = instructor.getCourses();

        // uklonim iz kursa tog instruktora
        for(Course course : courses){
            course.setInstructor(null);
        }

        // ovo ce onda da obrise i instructor details objekat jer je cascade postavljen na ALL
        // i nece kurseve jer sam tog instruktora izbacio sa svih njegovih kurseva
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
                                                                // IZ NEKOG RAZLOGA MORA OVAKAV QUERY DA BUDE, MSM NA VELIKA I MALA SLOVA
        TypedQuery<Course> typedQuery = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
                // IMENOVANI PARAMETAR U QUERY MORA DA IMA : ISPRED SAMOG IMENA, A SAMA VREDNOST SE POSLE DODELJUJE

        typedQuery.setParameter("data", theId);

        List<Course> courses = typedQuery.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> typedQuery = entityManager.createQuery(
                // PAZI!!! MORA NA KRAJU LINIJE DA SE UDARI SPACE, OSIM POSLEDNJE
                "select instructor from Instructor instructor " +
                "JOIN FETCH instructor.courses " + // JOIN FETCH je slicno kao EAGER loading, dobijam instruktora i sve kurseve za njega
                "JOIN FETCH instructor.instructorDetail " + //OVA LINIJA MOZE DA SE OBRISE, ISTI JE ISPIS SAMO JE SMANJEN BROJ QUERY-JA KOJI SE IZVODI
                        "where instructor.id = :data", Instructor.class);

        typedQuery.setParameter("data", theId);

        Instructor resInstructor = typedQuery.getSingleResult();

        return resInstructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        // MERGE metod azurira vec postojeci objekat
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
        // SACUVACE KURS I SVE RECENZIJE ZA TAJ KURS
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
}
