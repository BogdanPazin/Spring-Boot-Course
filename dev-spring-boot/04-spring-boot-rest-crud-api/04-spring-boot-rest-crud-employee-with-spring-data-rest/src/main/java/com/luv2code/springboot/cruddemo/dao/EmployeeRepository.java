package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// sa ovom anotacijom menjam default-ni naziv koji daje Spring Data REST za url
// umesto default-nog /employees, sada ce da bude /members
//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // ovaj interface ce da sluzi kao DAO
}