package com.goal.employeeservice.controller;

import com.goal.employeeservice.response.EmployeeResponse;
import com.goal.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @GetMapping("/employeesUsingFeign/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetailsUsingFeign(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeByIdUsingFeign(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}