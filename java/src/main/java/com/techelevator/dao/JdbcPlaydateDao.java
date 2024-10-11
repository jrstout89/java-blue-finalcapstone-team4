package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pets;
import com.techelevator.model.Playdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlaydateDao implements PlaydateDao {

    @Autowired
    private PetDao petDao;

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlaydateDao(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    private Playdate mapRowToPlaydate(SqlRowSet results) {
        Playdate playdate = new Playdate();
        playdate.setId(results.getInt("playdate_id"));
        playdate.setEventLocation(results.getString("event_location"));
        playdate.setMaximumPets(results.getInt("maximum_pets"));
        playdate.setEventHost(results.getInt("event_host"));
        playdate.setEventDate(LocalDate.parse(String.valueOf(results.getDate("event_date"))));
        playdate.setEventTime(LocalTime.parse(String.valueOf(results.getTime("event_time"))));
        playdate.setEventDuration(results.getInt("event_duration"));
        playdate.setEventTitle(results.getString("event_title"));
        playdate.setEventAddress(results.getString("event_address"));
        playdate.setEventDescription(results.getString("event_description"));
        playdate.setEventImage(results.getString("event_image"));
        playdate.setLatitude(results.getFloat("event_latitude"));
        playdate.setLongitude(results.getFloat("event_longitude"));
        return playdate;
    }

    // Method to view all available playdates.
    @Override
    public List<Playdate> getAllPlaydates(int custId) {
        List<Playdate> playdates = new ArrayList<>();
        // switch * to whatever i need from playdate table/
        String sql = "SELECT playdate.playdate_id, playdate.event_title, playdate.event_location, playdate.event_address, playdate.event_latitude,playdate.event_longitude, playdate.maximum_pets, playdate.event_host, playdate.event_date, playdate.event_time, playdate.event_duration, playdate.event_description, playdate.event_image, users.username FROM playdate join customers on playdate.event_host = customers.customer_id join users on customers.customer_id = users.user_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                playdates.add(mapRowToPlaydate(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        for (Playdate playdate : playdates) {
            List<Pets> pets = petDao.getPetsByCustomerId(custId);
            playdate.setPetCandidates(pets);
        }

        return playdates;
    }

    @Override
    public List<Playdate> getAllPlaydates() {
        List<Playdate> playdates = new ArrayList<>();
        // switch * to whatever i need from playdate table/
        String sql = "SELECT playdate.playdate_id, playdate.event_title, playdate.event_location, playdate.event_address, playdate.event_latitude,playdate.event_longitude, playdate.maximum_pets, playdate.event_host, playdate.event_date, playdate.event_time, playdate.event_duration, playdate.event_description, playdate.event_image, users.username FROM playdate join customers on playdate.event_host = customers.customer_id join users on customers.customer_id = users.user_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                playdates.add(mapRowToPlaydate(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return playdates;
    }

    // Method to search for a specific playdate by its ID.
    @Override
    public Playdate getPlaydateById(int id) {
        String sql = "SELECT * FROM playdate WHERE playdate_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                return mapRowToPlaydate(results);
            } else {

                // Handles invalid IDs.
                throw new DaoException("Playdate not found.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
    }

    @Override
    public List<Playdate> getPlaydateByUsername(String username) {
        List<Playdate> playdate = new ArrayList<>();
        String sql = "select playdate.playdate_id,playdate.event_title,playdate.event_location, playdate.event_address, playdate.event_latitude,playdate.event_longitude, playdate.maximum_pets, playdate.event_host, playdate.event_date, playdate.event_time, playdate.event_duration, playdate.event_description, playdate.event_image from playdate " +
                "join customers ON playdate.event_host = customers.customer_id " +
                "join users ON customers.user_id = users.user_id " +
                "where username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                playdate.add(mapRowToPlaydate(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
        return playdate;
    }

    // Method to create a new playdate.
    @Override
    public Playdate createPlaydate(Playdate playdate) {
        String sql = "INSERT INTO playdate (event_title, event_location, event_address, event_latitude, event_longitude, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            // Setting template update to "rowsAffected" to check for exceptions where no rows are inserted.
            int rowsAffected = jdbcTemplate.update(sql, playdate.getEventTitle(), playdate.getEventLocation(), playdate.getEventAddress(), playdate.getLatitude(),playdate.getLongitude(),playdate.getMaximumPets(), playdate.getEventHost(), playdate.getEventDate(), playdate.getEventTime(), playdate.getEventDuration(), playdate.getEventDescription(), playdate.getEventImage());

            // This will handle cases where no rows were inserted.
            if (rowsAffected == 0) {
                throw new DaoException("Failed to create playdate.");
            }
            return playdate;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
    }

    @Override
    public boolean updatePlaydate(Playdate playdate) {
        String sqlUpdate = "UPDATE playdate SET event_title = ?, " +
                "event_location = ?, event_address = ?, " +
                "event_latitude = ?, event_longitude = ?, " +
                " maximum_pets = ?, event_host = ?," +
                " event_date = ?, event_time = ?, " +
                "event_duration = ?, event_description = ?," +
                " event_image = ?  WHERE playdate_id = ?";
        int numberOfRows = 0;
        try{
            numberOfRows=jdbcTemplate.update(sqlUpdate, playdate.getEventTitle(), playdate.getEventLocation(),
                    playdate.getEventAddress(), playdate.getLatitude(), playdate.getLongitude(), playdate.getMaximumPets(), playdate.getEventHost(), playdate.getEventDate(),
                    playdate.getEventTime(), playdate.getEventDuration(), playdate.getEventDescription(),playdate.getEventImage(),
                    playdate.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows==1;
    }
    @Override
    public int deletePlaydateById(int id) {
        if(id<=0){
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        String sql = "DELETE FROM playdate WHERE playdate_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    //get playdates by pet id
    @Override
    public List<Playdate> getPlaydateByPetId(int petId){
        List<Playdate> playdates = new ArrayList<>();
        String sql = "SELECT * FROM playdate pd JOIN playdate_pets pp ON pd.playdate_id = pp.playdate_id " +
                "WHERE pp.pet_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
            while(results.next()){
                playdates.add(mapRowToPlaydate(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return playdates;
    }
    //remove pet from playdate
    @Override
    public void removePetFromPlaydate(int playdateId, int petId) {
        String sql = "DELETE FROM playdate_pets WHERE playdate_id = ? AND pet_id = ?";

        try {
            jdbcTemplate.update(sql, playdateId, petId);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Unable to remove pet from playdate due to data integrity violation.", e);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }
}







