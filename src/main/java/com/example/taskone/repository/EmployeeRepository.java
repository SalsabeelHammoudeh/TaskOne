package com.example.taskone.repository;
import com.example.taskone.model.Employee;
import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {
    public Long countById(Integer id);


    @Query(value = "SELECT * FROM employees JOIN depart_emp ON depart_emp.emp_id = employees.id JOIN departments ON depart_emp.depat_id = departments.id And departments.depart_manager = employees.id", nativeQuery = true)
    List<Employee> findAllManagers();

    @Query(value = "SELECT * FROM employees where employees.gender = :g", nativeQuery = true)
    List<Employee> findByGender(@Param("g") boolean g);

    @Query(value = "SELECT employees.name FROM employees JOIN depart_emp ON depart_emp.emp_id = employees.id JOIN departments ON depart_emp.depat_id = departments.id And departments.id = :id", nativeQuery = true)
    List<String>  employeesNamesOfDepartment(@Param("id") int id);



}
