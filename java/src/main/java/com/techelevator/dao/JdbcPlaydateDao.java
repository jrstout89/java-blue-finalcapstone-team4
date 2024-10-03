package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Playdate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcPlaydateDao implements PlaydateDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlaydateDao (DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }
    private Playdate mapRowToPlaydate (SqlRowSet results) {
        Playdate playdate = new Playdate();
        playdate.setId(results.getInt("playdate_id"));
        playdate.setEventLocation(results.getString("event_location"));
        playdate.setMaximumPets(results.getInt("maximum_pets"));
        playdate.setEventHost(results.getString("event_host"));
        playdate.setEventDate(String.valueOf(results.getDate("event_date")));
        playdate.setEventTime(String.valueOf(results.getTime("event_time")));
        playdate.setEventDuration(results.getInt("event_duration"));
        playdate.setEventTitle(results.getString("event_title"));
        playdate.setEventAddress(results.getString("event_address"));
        playdate.setEventDescription(results.getString("event_description"));
        playdate.setEventImage(results.getString("event_image"));
//        playdate.setStatus(Playdate.PlaydateStatus.valueOf(results.getString("playdate_status")));
        return playdate;
    }

    // Method to view all available playdates.
    @Override
    public List<Playdate> getAllPlaydates () {
        List<Playdate> playdates = new ArrayList<>();

        String sql = "SELECT * FROM playdate";
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

    // Method to create a new playdate.
    @Override
    public Playdate createPlaydate(Playdate playdate) {
        String sql = "INSERT INTO playdate (event_title, event_location, event_address, maximum_pets, event_host, event_date, event_time, event_duration, event_description, event_image, playdate_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            // Setting template update to "rowsAffected" to check for exceptions where no rows are inserted.
            int rowsAffected = jdbcTemplate.update(sql, playdate.getEventTitle(), playdate.getEventLocation(), playdate.getEventAddress(), playdate.getMaximumPets(), playdate.getEventHost(), playdate.getEventDate(), playdate.getEventTime(), playdate.getEventDuration(), playdate.getEventDescription(), playdate.getEventImage());

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
        String sqlSelect = "SELECT * FROM playdate WHERE id = ?";
        String sqlUpdate = "UPDATE playdate SET event_title = ?, " +
                "event_location = ?, event_address = ?," +
                " maximum_pets = ?, event_host = ?," +
                " event_date = ?, event_time = ?, " +
                "event_duration = ?, event_description = ?," +
                " event_image = ?  WHERE id = ?";
        int numberOfRows = 0;
        try{
         numberOfRows=jdbcTemplate.update(sqlUpdate, playdate.getEventTitle(), playdate.getEventLocation(),
                playdate.getEventAddress(), playdate.getMaximumPets(), playdate.getEventHost(), playdate.getEventDate(),
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
        String sql = "DELETE FROM playdate WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Method to accept a playdate request.
//    @Override
//    public Playdate acceptPlaydate(int id) {
//        String sql = "UPDATE playdate SET playdate_status = ? WHERE playdate_id = ?";
//        try {
//
//            // Setting template update to "rowsAffected" to check for instances where the playdate isn't found or has already been accepted. Using the same term to maintain naming conventions.
//            int rowsAffected = jdbcTemplate.update(sql, Playdate.PlaydateStatus.ACCEPTED, id);
//
//            // This will handle cases where the playdate can't be found or already has the accepted status.
//            if (rowsAffected == 0) {
//                throw new DaoException("Playdate not found or has already been accepted.");
//            }
//            return getPlaydateById(id);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to the database.", e);
//        }
//
//    }

    // Method to decline a playdate request.
//    @Override
//    public Playdate declinePlaydate(int id) {
//        String sql = "UPDATE playdate SET playdate_status = ? WHERE playdate_id = ?";
//        try {
//
//            // Setting template update to "rowsAffected" to check for instances where the playdate isn't found or has already been declined. Using the same term to maintain naming conventions.
//            int rowsAffected = jdbcTemplate.update(sql, Playdate.PlaydateStatus.DECLINED, id);
//
//            // This will handle cases where the playdate can't be found or already has the declined status.
//            if (rowsAffected == 0) {
//                throw new DaoException("Playdate not found or has already been declined.");
//            }
//            return getPlaydateById(id);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to the database.", e);
//        }
//    }
}
