package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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

	//ovo je za kreiranje command line aplikacije
	//preko koje se radi hibernate/jpa

	//ovaj deo koda ce se izvrsiti nakon sto se svi spring bean-ovi ucitaju
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);

			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

//			readAllStudents(studentDAO);

//			readAllStudentsByFirstName(studentDAO);

//			updateStudent(studentDAO);

//			removingStudent(studentDAO);

//			removeStudents(studentDAO);
		};
	}

	private void removeStudents(StudentDAO studentDAO) {
		System.out.println("Removing all students from the database...");
		studentDAO.deleteAllStudents();
	}

	private void removingStudent(StudentDAO studentDAO) {
		System.out.println("Deleting student with id 2 from the database");
		studentDAO.deleteStudent(2);
	}

	private void updateStudent(StudentDAO studentDAO) {
		System.out.println("Getting the student with the id 3");
		Student student = studentDAO.findById(3);

		System.out.println("Changing the last name of the student to Doo...");
		student.setLastName("Doo");

		studentDAO.updateStudent(student);

		System.out.println("Updated student: " + student);
	}

	private void readAllStudentsByFirstName(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findByFirstName("George");

		for(Student student : list){
			System.out.println(student);
		}
	}

	private void readAllStudents(StudentDAO studentDAO) {
		List<Student> list = studentDAO.findAll();

		for(Student student : list){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		Student student = new Student("John", "Doe", "johndoe@gmail.com");

		System.out.println("Saving the student");
		studentDAO.save(student);

		System.out.println("The student was saved. The id of the saved student is: " + student.getId());

		Student res = studentDAO.findById(student.getId());
		System.out.println("The retrieved student from the database using the id is: " + res);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new students");
		Student student1 = new Student("Paul", "Doe", "pauldoe@gmail.com");
		Student student2 = new Student("George", "Doe", "georgedoe@gmail.com");
		Student student3 = new Student("Ringo", "Doe", "ringodoe@gmail.com");

		System.out.println("Saving the student: " + student1);
		studentDAO.save(student1);

		System.out.println("Saving the student: " + student2);
		studentDAO.save(student2);

		System.out.println("Saving the student: " + student3);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating a new student");
		Student student = new Student("John", "Doe", "johndoe@gmail.com");

		System.out.println("Saving the new student...");
		studentDAO.save(student);

		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
