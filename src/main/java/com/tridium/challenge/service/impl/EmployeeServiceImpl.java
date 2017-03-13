package com.tridium.challenge.service.impl;

import com.tridium.challenge.service.EmployeeService;
import com.tridium.challenge.domain.Employee;
import com.tridium.challenge.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Employee.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save a employee.
     *
     * @param employee the entity to save
     * @return the persisted entity
     */
    @Override
    public Employee save(Employee employee) {
        log.debug("Request to save Employee : {}", employee);
        Employee result = employeeRepository.save(employee);
        return result;
    }

    /**
     *  Get all the employees.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        log.debug("Request to get all Employees");
        List<Employee> result = employeeRepository.findAll();

        return result;
    }

    /**
     *  Get one employee by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        Employee employee = employeeRepository.findOne(id);
        return employee;
    }

    /**
     *  Delete the  employee by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.delete(id);
    }
}
