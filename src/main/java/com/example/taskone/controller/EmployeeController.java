package com.example.taskone.controller;
import com.example.taskone.dto.EmployeeDto;
import com.example.taskone.execption.EmployeeNotFoundException;
import com.example.taskone.model.Address;
import com.example.taskone.service.EmployeeService;
import com.example.taskone.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public List<Employee> showEmployeeList(){
//        List<Employee> listEmployees = service.listAll();
//        return listEmployees;
//    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Employee> list = service.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto, RedirectAttributes ra){
        ra.addFlashAttribute("message", "the employee has been saved successfully.");
        service.save(convertToEntity(employeeDto));
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public Employee editEmployee(@PathVariable("id") Integer id,RedirectAttributes ra){
        try{
            Employee employee = service.get(id);

            employee.setAge(45);
            System.out.println(employee.toString());
            service.save(employee);
            return employee;
        }
        catch(EmployeeNotFoundException e){
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The Employee Id "+ id + " has been deleted");
        }
        catch(EmployeeNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "done deleting";
    }
    private Employee convertToEntity(EmployeeDto empDto){
        Employee employee = modelMapper.map(empDto, Employee.class);
        return employee;
    }
    private EmployeeDto convertToDto (Employee e){
        EmployeeDto empDto = modelMapper.map(e, EmployeeDto.class);
        return empDto;
    }
//    private EmployeeDto convertToDto (Employee e){
//        EmployeeDto empDto = new EmployeeDto();
//        empDto.setAge(e.getAge());
//        empDto.setGender(e.getGender());
//        empDto.setBaseSalary(e.getBaseSalary());
//        empDto.setId(e.getId());
//        empDto.setRole(e.getRole());
//        empDto.setName(e.getName());
//        empDto.setPhoneNumber(e.getPhoneNumber());
//        return empDto;
//    }
}
