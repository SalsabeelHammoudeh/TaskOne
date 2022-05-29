package com.example.taskone.repository;
import com.example.taskone.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {
    public Long countById(Integer id);


}
