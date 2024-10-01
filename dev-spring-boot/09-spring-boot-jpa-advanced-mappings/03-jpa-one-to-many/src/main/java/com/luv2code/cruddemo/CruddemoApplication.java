package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			//createInstructor(appDAO);

			//findInstructor(appDAO);

			//deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);

			//findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);

			//updateCourse(appDAO);

			//deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 11;

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

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("The instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());
		System.out.println("DONE!!!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding the instructor with id: " + theId);

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

		Instructor instructor = new Instructor("Madhu", "Patel", "madhu@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/chaddarby", "GUITAR!!!");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done!");
	}

}
