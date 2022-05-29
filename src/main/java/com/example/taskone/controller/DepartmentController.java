package com.example.taskone.controller;

import com.example.taskone.execption.DepartmentNotFoundException;
import com.example.taskone.execption.EmployeeNotFoundException;
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
import java.util.Set;

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
    public String saveDepartment(@RequestBody Department  department) throws EmployeeNotFoundException {
        Integer employeeId = 1;
        Employee e = service2.get(employeeId);
        e.setManages(department);
        department.setManager(e);
        service.save(department);
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Integer id, RedirectAttributes ra) throws EmployeeNotFoundException,DepartmentNotFoundException {
        Department department = service.get(id);
        department.setName("hi dep");
        service.save(department);
        return "done editing";

    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    Department addEmployeeToDepartment(@PathVariable Integer departmentId, @PathVariable Integer employeeId ) throws DepartmentNotFoundException, EmployeeNotFoundException {
        Department d = service.get(departmentId);
        Employee e = service2.get(employeeId);
        d.addEmployee(e);
        service.save(d);
        return d;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, RedirectAttributes ra){
        service.delete(id);
        ra.addFlashAttribute("message", "The Address Id "+ id + " has been deleted");
        return "done deleting";
    }





}
