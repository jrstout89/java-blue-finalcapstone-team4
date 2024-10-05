package com.techelevator.dao;

import com.techelevator.model.Comments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
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
        Integer newCommentId = jdbcTemplate.queryForObject(sql, Integer.class, comment.getForumId(), comment.getCustomerId(), comment.getCommentContent(), comment.getCreatedDate());
        comment.setCommentId(newCommentId);
        return comment;
    }

    // Gets all comments associated with a forumId.
    @Override
    public List<Comments> getCommentsForForum(int forumId) {
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE forum_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, forumId);
        while (results.next()) {
            comments.add(mapRowToComment(results));
        }
        return comments;
    }

    // Gets a specific comment by a commentId.
    @Override
    public Comments getCommentById(int commentId) {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, commentId);
        if (result.next()) {
            return mapRowToComment(result);
        } else {
            return null;
        }
    }

    // Update an existing comment.
    @Override
    public boolean updateComment(Comments comment) {
        String sql = "UPDATE comments SET forum_id = ?, customer_id = ?, comment_content = ?, created_date WHERE comment_id = ?";
        return jdbcTemplate.update(sql, comment.getForumId(), comment.getCustomerId(), comment.getCommentContent(), comment.getCreatedDate()) > 0;
    }

    // Delete an existing comment.
    @Override
    public boolean deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        return jdbcTemplate.update(sql, commentId) > 0;
    }
}
