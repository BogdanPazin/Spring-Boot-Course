package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetail(appDAO);
		};
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
		appDAO.save(instructor);
		System.out.println("Done!");
	}

}
