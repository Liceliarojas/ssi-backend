package com.ssi.ssi.domain.repository;

import com.ssi.ssi.domain.model.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autor Marco Herrera.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("select emplo from Employee emplo where emplo.firstName like CONCAT('%',:name,'%') or emplo.email like CONCAT('%',:name,'%')")
    List<Employee> searchByText(@Param("name") String name);

    @Query("SELECT p FROM Employee p WHERE p.firstName like CONCAT('%',:search,'%') OR p.lastName like CONCAT('%',:search,'%') OR p.ci like CONCAT('%',:search,'%')")
    List<Employee> find11(@Param("search") String search);
}
