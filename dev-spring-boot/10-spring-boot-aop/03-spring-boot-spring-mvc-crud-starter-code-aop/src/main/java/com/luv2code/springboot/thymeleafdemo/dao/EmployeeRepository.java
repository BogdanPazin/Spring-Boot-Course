package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //OVDE PRAVIM METOD ZA SPRING DATA JPA DA SORTIRA KORISNIKE PO PREZIMENU
    public List<Employee> findAllByOrderByLastName();
}
