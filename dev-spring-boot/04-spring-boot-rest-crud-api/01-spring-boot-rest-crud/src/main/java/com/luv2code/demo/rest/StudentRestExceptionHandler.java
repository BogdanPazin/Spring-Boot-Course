package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ova klasa ce da mi sluzi za global exception handling
// svi handleri ce biti na jednom mestu a ne rasprostranjeni u rest controller-ima
@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    // ova metoda ce da uhvati exception ako se baci
    // gde je genericki tip StudentErrorResponse zapravo body samog teksta koji se vraca kao rezultat
    public ResponseEntity<StudentErrorResponse> catchException(StudentNotFoundException exception){
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimeStamp((int) System.currentTimeMillis());

        ResponseEntity<StudentErrorResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        // prvi parametar konstuktora je telo poruke, a drugi je kod statusa
        return responseEntity;
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleAllExceptions(Exception exc){
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimeStamp((int) System.currentTimeMillis());

        ResponseEntity<StudentErrorResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }
}