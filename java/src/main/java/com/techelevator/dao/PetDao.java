package com.techelevator.dao;

import com.techelevator.model.Pets;

import java.util.List;

public interface PetDao {
    List<Pets> getAllPets();
    List<Pets> getPetsByCustomerId(int customerId);
    Pets getPetById(int id);
    boolean updatePet(Pets pet, int customerId);
    int deletePetById(int id);
    void addPet(Pets pet);
}
