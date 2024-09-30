package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplement implements EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee", Employee.class);

        List<Employee> list = typedQuery.getResultList();

        return list;
    }

    @Override
    public Employee getById(int id) {
        Employee emp = entityManager.find(Employee.class, id);

        return emp;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee emp = entityManager.merge(employee);
        /*
            merge metod radi tako sto prvo proverava da li je id==0
            u slucaju da jeste onda radi save/insert
            a ako nije id==0 onda radi update
         */

        return emp;
    }

    @Override
    public void deleteById(int id) {
        Employee emp = entityManager.find(Employee.class, id);

        entityManager.remove(emp);
    }
}
