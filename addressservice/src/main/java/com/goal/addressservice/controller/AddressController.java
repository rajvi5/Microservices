package com.goal.addressservice.controller;

import com.goal.addressservice.response.AddressResponse;
import com.goal.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController
{
    @Autowired
    private AddressService addressService;

    //http://localhost:8081/address-service/addressByEmpId/1
    @GetMapping("/addressByEmpId/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId) {
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

    //http://localhost:8081/address-service/address/1
    @GetMapping("/address/{id}")
    private ResponseEntity<AddressResponse> getAddressDetails(@PathVariable("id") int id) {
        AddressResponse addressResponse = addressService.findAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }
}
