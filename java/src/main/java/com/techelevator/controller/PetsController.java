package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Customers;
import com.techelevator.model.Pets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PetsController {
    @Autowired
    private PetDao petDao;
    @Autowired
    private UserDao userDao;
    @RequestMapping(path="/pets", method = RequestMethod.GET)
    public List<Pets> getAllPets(){
        return petDao.getAllPets();
    }
    @RequestMapping(path="/{customerId}/pets", method = RequestMethod.GET)
    public List<Pets> getAllPetsByCustomerId(@PathVariable int customerId, Principal principal){
        Customers customers = userDao.getCustomer(principal.getName());
        customerId = customers.getId();
        return petDao.getPetsByCustomerId(customerId);
    }
    @RequestMapping(path="/pet/{id}", method = RequestMethod.GET)
    public Pets getPetById(@PathVariable int id){
        return petDao.getPetById(id);
    }
    @RequestMapping(path="/update-pet", method = RequestMethod.PUT)
    public boolean updatePet(@RequestBody Pets pet, Principal principal){
        Customers customers = userDao.getCustomer(principal.getName());
        return petDao.updatePet(pet, customers.getId());
    }
    @RequestMapping(path="/pets/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable int id){
        petDao.deletePetById(id);
    }
    @RequestMapping(path = "/add-pet", method = RequestMethod.POST)
    public void addPet(@RequestBody Pets pet){
        petDao.addPet(pet);
    }

}
