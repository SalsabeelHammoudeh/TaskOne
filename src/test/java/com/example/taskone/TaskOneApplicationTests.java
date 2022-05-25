package com.example.taskone;
import com.example.taskone.model.Address;
import com.example.taskone.repository.AddressRepository;
import com.example.taskone.repository.DepartmentRepository;
import com.example.taskone.model.Employee;
import com.example.taskone.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class TaskOneApplicationTests {
    @Autowired
    private EmployeeRepository repo;
    @Autowired
    private DepartmentRepository repo2;
    @Autowired
    private AddressRepository repo3;
//    @Autowired
//    private EmployeeController controller;

    @Test
    public void test4(){
        System.out.println("hi");
    }

    @Test
    public void test2(){
        Employee employee = new Employee();
        Address a = new Address();
        a.setLocation("location");
        employee.setAddress(a);
        employee.setName("Salsabeel");
        employee.setPhoneNumber("0507876885");
        employee.setAge(20);
        employee.setBaseSalary(10000);
        employee.setGender(true);
        employee.setHireDate(new Date());
        //employee.setCurrentSalary(1000);
        employee.setRole("role");
        Employee savedEmployee = repo.save(employee);
        Assertions.assertNotNull(savedEmployee);
        Assertions.assertTrue(savedEmployee.getId() >0);
    }

//    @Test
//    public void testAddNew(){
//        Employee employee = new Employee();
//        Address a = new Address();
//        a.setLocation("location");
//        employee.setAddress(a);
//        employee.setName("Salsabeel");
//        employee.setPhoneNumber("050787666");
//        employee.setAge(20);
//        employee.setBaseSalary(10000);
//        employee.setGender(true);
//        employee.setHireDate(new Date());
//        employee.setRole("role");
//        Department d = new Department();
//        d.setName("hi");
//        // d.getEmployees().add(employee);
//        employee.getDepartments().add(d);
//
//        employee.setManages(d);
//        //d.setManager(employee);
//        //repo2.save(d);
//        Employee savedEmployee = repo.save(employee);
//        Assertions.assertNotNull(savedEmployee);
//        Assertions.assertTrue(savedEmployee.getId() >0);
//    }
//
    @Test
    public void test1(){
        Integer employeeId = 1;
        Employee updatedEmployee = repo.findById(employeeId).get();
        System.out.println(updatedEmployee.toString());
        Address a = new Address();
        a.setLocation("location");
        a.getEmployees().add(updatedEmployee);
        repo3.save(a);
    }

//    @Test
//    public void test3(){
//   List<Employee> employeeList ;
//   employeeList = controller.showEmployeeList();
//   System.out.println(employeeList.toString());
//
//
//    }

//
//    @Test
//    public void testListAll(){
//        Iterable<Employee> employees = repo.findAll();
//        Assertions.assertTrue(((Collection<Employee>) employees).size() >0);
//        for (Employee employee: employees){
//            System.out.println(employee);
//        }
//    }
//    @Test
//    public void updateEmployee(){
//        Integer employeeId = 1; // the user to update
//        Optional<Employee> optionalEmployee = repo.findById(employeeId);
//        Employee employee = optionalEmployee.get();
//        employee.setName("Mira");
//        repo.save(employee);
//        Employee updatedEmployee = repo.findById(employeeId).get();
//        Assertions.assertTrue(updatedEmployee.getName() == "Mira");
//    }
//    @Test
//    public void testGet(){
//        Integer employeeId = 1; // the user to update
//        Optional<Employee> optionalEmployee = repo.findById(employeeId);
//        Assertions.assertTrue(optionalEmployee.isPresent());
//        System.out.println(optionalEmployee.get());
//    }
//    @Test
//    public void testDelete(){
//        Integer employeeId = 4; // the user to delete
//        repo.deleteById(employeeId);
//        Optional<Employee> optionalEmployee = repo.findById(employeeId);
//        Assertions.assertFalse(optionalEmployee.isPresent());
//    }


}
