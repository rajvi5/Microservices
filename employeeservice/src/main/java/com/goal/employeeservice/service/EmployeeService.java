package com.goal.employeeservice.service;

import com.goal.employeeservice.entity.Employee;
import com.goal.employeeservice.feignclient.AddressClient;
import com.goal.employeeservice.repository.EmployeeRepo;
import com.goal.employeeservice.response.AddressResponse;
import com.goal.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    //When using feign client.
    @Autowired
    private AddressClient addressClient;

    public EmployeeResponse getEmployeeById(int id)
    {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        //Communicating to addressService using restTemplate
        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address-service/addressByEmpId/{id}", AddressResponse.class, id);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

    public EmployeeResponse getEmployeeByIdUsingFeign(int id)
    {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        //Communicating to addressService using  FeignClient
        ResponseEntity<AddressResponse> addressResponseFeign = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponseFeign.getBody());
        return employeeResponse;
    }
}
