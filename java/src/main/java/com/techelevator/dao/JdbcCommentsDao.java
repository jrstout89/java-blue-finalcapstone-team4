package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comments;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentsDao implements CommentsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Comments mapRowToComment(SqlRowSet results) {
        Comments comments = new Comments();
        comments.setCommentId(results.getInt("comment_id"));
        comments.setForumId(results.getInt("forum_id"));
        comments.setCustomerId(results.getInt("customer_id"));
        comments.setCommentContent(results.getString("comment_content"));
        comments.setUsername(results.getString("username"));

        Date createdDate = results.getDate("created_date");
        if (createdDate != null) {
            comments.setCreatedDate(createdDate.toLocalDate().toString());
        }
        return comments;
    }

    // Create/add a new comment.
    @Override
    public Comments addComment(Comments comment) {
        String sql = "INSERT INTO comments (forum_id, customer_id, comment_content, created_date) VALUES (?, ?, ?, ?) RETURNING comment_id";
        try{
            Integer newCommentId = jdbcTemplate.queryForObject(sql, Integer.class, comment.getForumId(), comment.getCustomerId(), comment.getCommentContent(), LocalDateTime.now());
            comment.setCommentId(newCommentId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return comment;
    }

    // Gets all comments associated with a forumId.
    @Override
    public List<Comments> getCommentsForForum(int forumId) {
        List<Comments> comments = new ArrayList<>();
//        String sql = "SELECT * FROM comments WHERE forum_id = ?";
        String sql = "SELECT comments.comment_id, comments.forum_id, comments.customer_id, comments.comment_content, comments.created_date, users.username " +
                "FROM comments " +
                "JOIN users ON comments.customer_id = users.user_id " +
                "WHERE comments.forum_id = ? " +
                "ORDER BY comments.created_date ASC";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
            while (results.next()) {
                comments.add(mapRowToComment(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return comments;
    }

    // Gets a specific comment by a commentId.
    @Override
    public Comments getCommentById(int commentId) {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, commentId);
            if (result.next()) {
                return mapRowToComment(result);
            } else {
                return null;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
    }

    // Update an existing comment.
    @Override
    public boolean updateComment(Comments comment) {
        String sql = "UPDATE comments SET forum_id = ?, customer_id = ?, comment_content = ?, created_date = ? WHERE comment_id = ?";
        try {
            return jdbcTemplate.update(sql, comment.getForumId(), comment.getCustomerId(), comment.getCommentContent(), LocalDateTime.now(), comment.getCommentId()) > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    // Delete an existing comment.
    @Override
    public boolean deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        try {
            return jdbcTemplate.update(sql, commentId) > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
    }
}
