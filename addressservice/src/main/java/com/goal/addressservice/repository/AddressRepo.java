package com.goal.addressservice.repository;


import com.goal.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>
{
    @Query(
            nativeQuery = true,
            value = "SELECT ea.id, ea.city, ea.state FROM practice.address ea join practice.employees e on e.emp_id = ea.emp_id where ea.emp_id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
