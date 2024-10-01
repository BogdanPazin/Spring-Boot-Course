package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        Student stud1 = new Student("John", "Terry");
        Student stud2 = new Student("Paul", "Scholes");
        Student stud3 = new Student("Steven", "Gerrard");

        theStudents.add(stud1);
        theStudents.add(stud2);
        theStudents.add(stud3);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student not found with id " + studentId);
        }

        return theStudents.get(studentId);
    }
}
