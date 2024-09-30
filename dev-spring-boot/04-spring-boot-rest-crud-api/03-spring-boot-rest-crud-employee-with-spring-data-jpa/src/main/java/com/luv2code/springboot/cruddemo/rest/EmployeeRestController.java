package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    /*
        POSTO SAM UBACIO SERVICE U APLIKACIJU, CONTROLLER NECE VISE DA KORISTI DAO
        VEC CE DA KORISTI SERVICE KOJI KORISTI TAJ DAO
     */
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        Employee emp = employeeService.getById(employeeId);

        if(emp == null){
            throw new RuntimeException("Employee not found with the given id: " + employeeId);
        }

        return emp;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        // koristi se @RequestBody anotacija zbog toga jer se podaci za novog employee-a
        // salju kao json tekst, i pomocu ove anotacije ce se povezati ti podaci sa ovim poslatim objektom

        // u slucaju da se u json tekstu posalje id, podesava se da on bude 0
        // tako da izazove save (a ne update) u metodi merge

        employee.setId(0);
        Employee newEmployee = employeeService.saveEmployee(employee);
        return newEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.saveEmployee(employee);
        return emp;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.getById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Employee with id: " + employeeId + " has been deleted";
    }
}
