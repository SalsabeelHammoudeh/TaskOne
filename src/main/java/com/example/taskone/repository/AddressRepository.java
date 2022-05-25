package com.example.taskone.repository;
import com.example.taskone.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address,Integer> {
    public Long countById(Integer id);
}
