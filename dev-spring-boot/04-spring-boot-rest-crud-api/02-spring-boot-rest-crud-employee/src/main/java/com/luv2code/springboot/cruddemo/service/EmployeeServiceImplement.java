package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ova anotacija je jos jedna podanotacija od @Component
// sa njom se omogucava component scanning od strane spring boot-a
// ova klasa se nalazi izmedju controller-a i DAOImplement-a
@Service
public class EmployeeServiceImplement implements EmployeeService{
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImplement(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}