package com.tridium.challenge.repository;

import com.tridium.challenge.domain.Employee;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
