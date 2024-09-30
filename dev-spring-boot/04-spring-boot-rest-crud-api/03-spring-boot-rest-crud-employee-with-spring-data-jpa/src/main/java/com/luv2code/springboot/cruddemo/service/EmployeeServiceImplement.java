package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ova anotacija je jos jedna podanotacija od @Component
// sa njom se omogucava component scanning od strane spring boot-a
// ova klasa se nalazi izmedju controller-a i DAOImplement-a
@Service
public class EmployeeServiceImplement implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplement(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else{
            throw new RuntimeException("Employee with id " + id + " not found");
        }

        return employee;
    }

    @Override
    // ne mora da se pise transactional jer jpa repository radi s tim
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    // ne mora da se pise transactional jer jpa repository radi s tim
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}