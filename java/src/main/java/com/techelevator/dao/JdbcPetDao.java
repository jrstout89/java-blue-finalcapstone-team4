package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pets;
import com.techelevator.model.PlaydatePets;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPetDao implements PetDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private Pets mapRowToPet(SqlRowSet results){
        Pets pets = new Pets();
        pets.setId(results.getInt("pet_id"));
        pets.setName(results.getString("pet_name"));
        pets.setBreed(results.getString("breed"));
        pets.setDateOfBirth(results.getDate("date_of_birth").toLocalDate());
        pets.setGender(results.getString("gender"));
        pets.setCustomerId(results.getInt("customer_id"));
        pets.setPetSize(results.getString("pet_size"));
        pets.setVaccination(results.getBoolean("vaccination"));
        pets.setSpayNeuter(results.getBoolean("is_spay_neuter"));
        pets.setEnergyLevel(results.getString("energy_level"));
        pets.setPersonality(results.getString("personality"));
        pets.setImage(results.getString("image"));
        return pets;
    }
    @Override
    public List<Pets> getAllPets() {
        List<Pets> pet = new ArrayList<>();
        String sql="SELECT * FROM pets;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                pet.add(mapRowToPet(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return pet;
    }

    @Override
    public List<Pets> getPetsByCustomerId(int customerId) {
        List<Pets> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE customer_id = ?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, customerId);
            while(result.next()){
                pets.add(mapRowToPet(result));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return pets;
    }

    @Override
    public Pets getPetById(int id) {
        Pets pets = null;
        String sql = "SELECT * FROM pets WHERE pet_id = ?";
//        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if (result.next()){
               pets = mapRowToPet(result);
            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
        return pets;
    }

    @Override
    public boolean updatePet(Pets pet, int customerId) {
        String sql = "UPDATE pets SET pet_name = ?, breed = ?, date_of_birth = ?," +
                "gender = ?, customer_id = ?, pet_size = ?, vaccination = ?," +
                "is_spay_neuter = ?, energy_level = ?, personality = ?, image = ? " +
                "WHERE pet_id = ?";

        int numberOfRow = 0;
        try {
            numberOfRow = jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getDateOfBirth(),
                    pet.getGender(), customerId, pet.getPetSize(), pet.isVaccination(), pet.isSpayNeuter(),
                    pet.getEnergyLevel(), pet.getPersonality(), pet.getImage(), pet.getId());

            // Check if any rows were updated
            if (numberOfRow == 0) {
                throw new DaoException("No pet found with ID: " + pet.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRow == 1;
    }

    @Override
    public void deletePetById(int id) {
        if(id<=0){
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        String sql = "DELETE FROM pets WHERE pet_id = ?";
        try{
            jdbcTemplate.update(sql,id);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void addPet(Pets pet) {
        String sql = "INSERT INTO pets (pet_name, breed, date_of_birth, gender, customer_id, pet_size, vaccination, is_spay_neuter, energy_level, personality, image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getDateOfBirth(),
                    pet.getGender(), pet.getCustomerId(), pet.getPetSize(),
                    pet.isVaccination(), pet.isSpayNeuter(), pet.getEnergyLevel(),
                    pet.getPersonality(), pet.getImage());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void linkPetPlaydate(PlaydatePets playdatePets) {
        String sql = "INSERT INTO playdate_pets (playdate_id, pet_id) VALUES(?, ?)";
        try{
            jdbcTemplate.update(sql, playdatePets.getPlaydateId(), playdatePets.getPetId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Pets> getPetsByPlaydateId(int playdateId) {
        List<Pets> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets JOIN playdate_pets on pets.pet_id = playdate_pets.pet_id WHERE playdate_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playdateId);
            while (results.next()){
                pets.add(mapRowToPet(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return pets;
    }

}
