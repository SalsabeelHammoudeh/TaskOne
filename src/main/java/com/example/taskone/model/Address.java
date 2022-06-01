package com.example.taskone.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;


@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "location", length = 200)
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy="address", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployee(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }
}
