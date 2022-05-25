package com.example.taskone.service;

import com.example.taskone.model.Address;
import com.example.taskone.model.Department;
import com.example.taskone.model.Employee;
import com.example.taskone.repository.AddressRepository;
import com.example.taskone.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repo;

    public List<Department> getAllDepartments(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Department> pagedResult = repo.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Department>();
        }
    }
    public void save(Department department) {

        repo.save(department);
    }

    public Department get(Integer id){
        Optional<Department> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        else
            return null;
    }

    public void delete(Integer id){
        Long count = repo.countById(id);
        if (count == null || count == 0){
            System.out.println("Could not find any Department with ID "+ id);
        }
        repo.deleteById(id);
    }
}
