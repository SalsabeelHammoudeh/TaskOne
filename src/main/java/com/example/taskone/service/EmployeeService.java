package com.example.taskone.service;
import antlr.ASTNULLType;
import com.example.taskone.dto.EmployeeDto;
import com.example.taskone.execption.EmployeeNotFoundException;
import com.example.taskone.model.Employee;
import com.example.taskone.repository.EmployeeRepository;
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
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    public List<Employee> listAll(){

        return (List<Employee>) repo.findAll();
    }

    public void save(Employee employee) {

        repo.save(employee);
    }

    public Employee get(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any users with ID "+ id);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new EmployeeNotFoundException("Could not find any users with ID "+ id);
        }
        repo.deleteById(id);
    }

    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = repo.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }
}
