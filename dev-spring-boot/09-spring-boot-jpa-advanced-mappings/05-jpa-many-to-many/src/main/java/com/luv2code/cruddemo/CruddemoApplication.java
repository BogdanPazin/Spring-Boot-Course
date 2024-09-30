package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//ovaj deo se izvrsava nakon sto se svi bean-ovi ucitaju
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			//createCourseAndStudents(appDAO);

			//findCourseWithStudents(appDAO);

			//findStudentWithCourses(appDAO);

			//addCoursesToStudent(appDAO);

			//deleteCourse(appDAO);

			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Deleting student with id: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("DONE!!!");
	}

	private void addCoursesToStudent(AppDAO appDAO) {
		int theId = 2;

		Student student = appDAO.findStudentWithCoursesByStudentId(theId);

		Course course1 = new Course("How to Tech");
		Course course2 = new Course("Learning English");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student: " + student);
		System.out.println("Associated courses: " + student.getCourses());

		appDAO.updateStudent(student);
		System.out.println("DONE!!");
	}

	private void findStudentWithCourses(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding student with id: " + theId);

		Student student = appDAO.findStudentWithCoursesByStudentId(theId);

		System.out.println("The student: " + student);

		System.out.println("Associated courses: " + student.getCourses());

		System.out.println("DONE!!");
	}

	private void findCourseWithStudents(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Finding course with id: " + theId);

		Course course = appDAO.findCourseWithStudentsByCourseId(theId);

		System.out.println("The course: " + course);

		System.out.println("Associated students: " + course.getStudents());

		System.out.println("DONE!!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("OOP");

		Student student1 = new Student("Bogdan", "Pazin", "bogdan@test.com");
		Student student2 = new Student("Milos", "Pazin", "milos@test.com");
		Student student3 = new Student("Lidija", "Andrejevic-Pazin", "lidija@test.com");

		course.addStudent(student1);
		course.addStudent(student2);
		course.addStudent(student3);

		System.out.println("Saving the course: " + course);
		System.out.println("Saving associated students: " + course.getStudents());

		appDAO.saveCourse(course);
		System.out.println("DONE!!!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 11;

		System.out.println("Deleting course with id: " + theId);

		appDAO.deleteCourseById(theId); //BRISE KURS I SVE RECENZIJE ZA NJEGA JER JE CASCADE POSTAVLJEN NA ALL

		System.out.println("Done!!!");
	}

	private void getCourseWithReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Finding course with id: " + theId);

		Course course = appDAO.findCourseWithReviewsByCourseId(theId);

		System.out.println("The course: " + course);

		System.out.println("The reviews: " + course.getReviews());

		System.out.println("DONE!!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Beauty of Football");

		course.addReview(new Review("Great course... loved it!"));
		course.addReview(new Review("Cool course!"));
		course.addReview(new Review("Stupid"));

		System.out.println("Saving the course...");
		System.out.println("The course: " + course);
		System.out.println("The reviews: " + course.getReviews());

		appDAO.saveCourse(course); // CUVA KURS I RECENZIJE ZA NJEGA
		System.out.println("Done!!!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 12;

		System.out.println("Deleting course with id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 12;

		System.out.println("Finding course with id: " + theId);
		Course course = appDAO.findCourseById(theId);

		System.out.println("Updating course title with id: " + theId);
		course.setTitle("Football Masterclass");

		appDAO.updateCourse(course);

		System.out.println("Done!!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding the instructor with id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Updating instructor with id: " + theId);

		instructor.setLastName("TESTER");

		appDAO.updateInstructor(instructor);

		System.out.println("DONE!!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding the instructor with id: " + theId);

		// ovaj metod vraca instruktora sa svim kursevima
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("The instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());
		System.out.println("DONE!!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding the instructor with id: " + theId);

		// OVAKO VRACA SAMO INSTRUKTORA, JER JE FETCH POSTAVLJEN NA LAZY
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("The instructor: " + instructor);

		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courseList = appDAO.findCoursesByInstructorId(theId);

		instructor.setCourses(courseList);

		System.out.println("Associated courses: " + instructor.getCourses());

		System.out.println("Done!!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding the instructor with id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("The instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());

		System.out.println("Done!!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Susan", "Public", "susan@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/susan", "CATS!!!");

		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Air Guitar - The Ultimate Guide");
		Course course2 = new Course("The Pinball Masterclass");
		Course course3 = new Course("Football Genius");

		instructor.addCourse(course1);
		instructor.addCourse(course2);
		instructor.addCourse(course3);

		System.out.println("Saving instructor: " + instructor);
		System.out.println("The courses: " + instructor.getCourses());
		// ovo ce da sacuva i kurseve zbog cascade-a
		appDAO.save(instructor);
		System.out.println("DONE!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("DONE!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("Instructor detail: " + instructorDetail);

		System.out.println("Associated instructor: " + instructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Deleting instructor with id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 2;

		System.out.println("Finding instructor with id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("Instructor details: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
//		Instructor instructor = new Instructor("Chad", "Darby", "darby@gmail.com");
//
//		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/chaddarby", "LUV 2 CODE!!!");

		Instructor instructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/chaddarby", "GUITAR!!!");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: " + instructor);
		// ovo ce takodje da sacuva i instructorDetails objekat
		appDAO.save(instructor);
		System.out.println("Done!");
	}

}
