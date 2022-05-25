package com.example.taskone.controller;

import com.example.taskone.execption.EmployeeNotFoundException;
import com.example.taskone.model.Address;
import com.example.taskone.model.Department;
import com.example.taskone.model.Employee;
import com.example.taskone.service.DepartmentService;
import com.example.taskone.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @Autowired
    private EmployeeService service2;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Department> list = service.getAllDepartments(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Department>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/save")
    public String saveDepartment(@RequestBody Department  department, RedirectAttributes ra){
        ra.addFlashAttribute("message", "the Address has been saved successfully.");
        service.save(department);
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Integer id, RedirectAttributes ra) throws EmployeeNotFoundException {
        Department department = service.get(id);
        Integer id2 = 1;
        Employee e = service2.get(id2);
        department.setManager(e);
        service.save(department);
        return "done editing";

    }
    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, RedirectAttributes ra){
        service.delete(id);
        ra.addFlashAttribute("message", "The Address Id "+ id + " has been deleted");
        return "done deleting";
    }




}
