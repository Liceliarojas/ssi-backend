package com.ssi.ssi.service;

import com.ssi.ssi.domain.model.Employee;
import com.ssi.ssi.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoCompleteService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Iterable<Employee> findAllEmployeeByParameter(String search) {
        return employeeRepository.findEmployee(search);
    }
}
