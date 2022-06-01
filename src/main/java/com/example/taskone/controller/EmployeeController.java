package com.example.taskone.controller;
import com.example.taskone.dto.EmployeeDto;
import com.example.taskone.execption.EmployeeNotFoundException;
import com.example.taskone.model.Address;
import com.example.taskone.model.Department;
import com.example.taskone.service.AddressService;
import com.example.taskone.service.DepartmentService;
import com.example.taskone.service.EmployeeService;
import com.example.taskone.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Autowired
    private AddressService service2;

    @Autowired
    private ModelMapper modelMapper;

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
    public Employee editEmployee(@PathVariable("id") Integer id){
        try{
            Employee employee = service.get(id);
            employee.setAge(45);
            Integer i = 1;
            Address a = service2.get(i);
            a.setLocation("new location");
            employee.setAddress(a);
            a.addEmployee(employee);
            service.save(employee);
            service2.save(a);
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

    @GetMapping("/managers")
    public List<Employee> getAllManagers()
    {
        List<Employee> list = service.getAllManagers();
        return list;
    }

    @GetMapping("/females")
    public List<Employee> getAllFemales()
    {
        List<Employee> list = service.findByGender(false);
        return list;
    }
    @GetMapping("/males")
    public List<Employee> getAllMales()
    {
        List<Employee> list = service.findByGender(true);
        return list;
    }
    @GetMapping("/department/{id}")
    public List<String>  getEmployeesNamesOfDepartment(@PathVariable("id") Integer id)
    {
        List<String> list = service.employeesOfDepartment(id);
        return list;
    }

    private Employee convertToEntity(EmployeeDto empDto){
        Employee employee = modelMapper.map(empDto, Employee.class);
        return employee;
    }
    private EmployeeDto convertToDto (Employee e){
        EmployeeDto empDto = modelMapper.map(e, EmployeeDto.class);
        return empDto;
    }

}
