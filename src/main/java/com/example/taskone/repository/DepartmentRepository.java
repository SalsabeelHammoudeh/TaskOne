package com.example.taskone.repository;
import com.example.taskone.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department,Integer> {
    public Long countById(Integer id);
}
