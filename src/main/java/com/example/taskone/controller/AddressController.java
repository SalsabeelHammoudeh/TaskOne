package com.example.taskone.controller;
import com.example.taskone.model.Address;
import com.example.taskone.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public List<Address> showAddressesList(){
        List<Address> listAddresses = service.listAll();
        return  listAddresses;
    }

    @PostMapping("/save")
    public String saveAddress(@RequestBody Address  address, RedirectAttributes ra){
        ra.addFlashAttribute("message", "the Address has been saved successfully.");
        service.save(address);
        return "saved";
    }

    @PutMapping("/edit/{id}")
    public String editAddress(@PathVariable("id") Integer id, RedirectAttributes ra){
            Address address = service.get(id);
            address.setLocation("here new location");
            service.save(address);
            return "done editing";

    }
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Integer id, RedirectAttributes ra){
            service.delete(id);
            ra.addFlashAttribute("message", "The Address Id "+ id + " has been deleted");
        return "done deleting";
    }

}
