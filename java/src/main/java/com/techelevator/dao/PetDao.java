package com.techelevator.dao;

import com.techelevator.model.Pets;
import com.techelevator.model.PlaydatePets;

import java.util.List;

public interface PetDao {
    List<Pets> getAllPets();
    List<Pets> getPetsByCustomerId(int customerId);
    Pets getPetById(int id);
    boolean updatePet(Pets pet, int customerId);
    void deletePetById(int id);
    void addPet(Pets pet);
    void linkPetPlaydate(PlaydatePets playdatePets);
    List<Pets> getPetsByPlaydateId(int playdateId);
}
