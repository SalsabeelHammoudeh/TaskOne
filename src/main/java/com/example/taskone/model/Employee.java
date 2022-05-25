package com.example.taskone.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

import static java.util.Calendar.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "phoneNumber", unique = true, length = 45)
    private String phoneNumber;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="employee_Address", nullable = false)
    private Address address = new Address();

    @Column(name = "role", length = 80)
    private String role;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "baseSalary")
    private double baseSalary;

    @Transient
    private double currentSalary;

    @Column(name = "hireDate", length = 45)
    private Date hireDate;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    private Department manages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employees")
    private Set<Department> departments = new HashSet<>();

    public Employee() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {

        return address;
    }

    public void setAddress(Address address) {

        this.address = address;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    public boolean getGender() {

        return gender;
    }

    public void setGender(boolean gender) {

        this.gender = gender;
    }

    public double getBaseSalary() {

        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {

        this.baseSalary = baseSalary;
    }

    public double getCurrentSalary() {
        int years = getDiffYears(hireDate, new Date());
        currentSalary = baseSalary + 200 * years;
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {

        this.currentSalary = currentSalary;
    }

    public Date getHireDate() {

        return hireDate;
    }

    public void setHireDate(Date hireDate) {

        this.hireDate = hireDate;
    }

    public Set<Department> getDepartments() {

        return departments;
    }

    public void setDepartments(Set<Department> departments) {

        this.departments = departments;
    }

    public Department getManages() {

        return manages;
    }

    public void setManages(Department manages) {

        this.manages = manages;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", role='" + role + '\'' +
                ", gender=" + gender +
                ", baseSalary=" + baseSalary +
                ", currentSalary=" + currentSalary +
                ", hireDate=" + hireDate +
                ", manages=" + manages +
                ", departments=" + departments +
                '}';
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
