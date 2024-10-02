package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Playdate;
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
        playdate.setEventLocation(results.getString("event_Location"));
        playdate.setMaximumPets(results.getInt("maximum_pets"));
        playdate.setEventHost(results.getString("event_host"));
        playdate.setEventDate(String.valueOf(results.getDate("event_date")));
        playdate.setEventTime(String.valueOf(results.getTime("event_time")));
        playdate.setEventDuration(results.getInt("event_duration"));
        playdate.setEventTitle(results.getString("event_title"));
        playdate.setEventAddress(results.getString("event_address"));
        playdate.setEventDescription(results.getString("event_description"));
        playdate.setEventImage(results.getString("event_image"));
        return playdate;
    }

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

}
