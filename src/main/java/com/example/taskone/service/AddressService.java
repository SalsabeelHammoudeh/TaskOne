package com.example.taskone.service;

import com.example.taskone.execption.EmployeeNotFoundException;
import com.example.taskone.model.Address;
import com.example.taskone.model.Employee;
import com.example.taskone.repository.AddressRepository;
import com.example.taskone.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repo;

    public List<Address> listAll(){

        return (List<Address>) repo.findAll();
    }

    public void save(Address address) {

        repo.save(address);
    }

    public Address get(Integer id){
        Optional<Address> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        else
            return null;
    }

    public void delete(Integer id){
        Long count = repo.countById(id);
        if (count == null || count == 0){
            System.out.println("Could not find any users with ID "+ id);
        }
        repo.deleteById(id);
    }

}
