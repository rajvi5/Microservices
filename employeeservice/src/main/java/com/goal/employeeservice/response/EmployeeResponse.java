package com.goal.employeeservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    private AddressResponse addressResponse;

}
