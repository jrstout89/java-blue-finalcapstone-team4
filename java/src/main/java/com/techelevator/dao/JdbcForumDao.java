package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comments;
import com.techelevator.model.Forum;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcForumDao implements ForumDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcForumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Added updates for new/changed variables.
    private Forum mapRowToForum(SqlRowSet results){
      Forum forum = new Forum();
      forum.setId(results.getInt("forum_id"));
      forum.setCustomerId(results.getInt("customer_id"));
      forum.setForumTitle(results.getString("forum_title"));

      Date createdDate = results.getDate("created_date");
      if (createdDate != null) {
          forum.setCreatedDate(createdDate.toLocalDate().toString());
      }

      Date updateDate = results.getDate("update_date");
      if (updateDate != null) {
          forum.setUpdateDate(updateDate.toLocalDate().toString());
      }

//      forum.setComment(results.getString("comment"));
//      List<Comments> comments = commentsDao.getCommentsForForum(forum.getId());
//      forum.setComments(comments);
      return forum;
    }
    @Override
    public void createForum(Forum forum) {
        String sql = "INSERT INTO forum (customer_id, forum_title, forum_content, created_date, update_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, forum.getCustomerId(), forum.getForumTitle(), forum.getForumContent(), LocalDateTime.now(), LocalDateTime.now());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public Forum getForumById(int id) {
        Forum forum = null;
        String sql = "SELECT * FROM forum WHERE forum_id=?";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            if(result.next()){
                forum = mapRowToForum(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return forum;
    }

    @Override
    public void updateForum(Forum forum) {
        String sql = "UPDATE forum SET customer_id = ?, forum_title = ?, update_date = ?" +
                "WHERE forum_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, forum.getCustomerId(), forum.getForumTitle(),
                    java.sql.Date.valueOf(forum.getUpdateDate()), forum.getId());
            if (rowsAffected == 0) {
                throw new DaoException("No forum found with ID: " + forum.getId());
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteForum(int id) {
        if(id <= 0){
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        String sql = "DELETE FROM forum WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public List<Forum> getAllForums() {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * FROM forum;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                forums.add(mapRowToForum(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return forums;
    }

    @Override
    public List<Forum> getForumsByCustomerId(int customerId) {
        List<Forum> forums = new ArrayList<>();
        String sql = "SELECT * FROM forum WHERE customer_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
            while (results.next()){
                forums.add(mapRowToForum(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return forums;
    }
}
