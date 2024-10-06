package com.techelevator.controller;

import com.techelevator.dao.CommentsDao;
import com.techelevator.dao.CustomersDao;
import com.techelevator.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CommentsController {

    @Autowired
    private CommentsDao commentsDao;

    @Autowired
    private CustomersDao customersDao;

    // Method to add a new comment.
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/add-comment", method = RequestMethod.POST)
    public void addComment(@Valid @RequestBody Comments comment, Principal principal) {
        String username = principal.getName();

        // This gets the customer ID.
        int customerId = customersDao.getCustomerIdByUsername(username);

        // This sets the customer ID on the comment.
        comment.setCustomerId(customerId);

        commentsDao.addComment(comment);
    }

    // Method to get all of the comments for a specific forum post.
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/forum/comments/{forumId}", method = RequestMethod.GET)
    public List<Comments> getCommentsForForum(@PathVariable int forumId) {
        return commentsDao.getCommentsForForum(forumId);
    }

    // Method to get a comment by it's comment ID.
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/comments/{commentId}", method = RequestMethod.GET)
    public Comments getCommentById(@PathVariable int commentId) {
        return commentsDao.getCommentById(commentId);
    }

    // Method to update a comment.
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/update-comment/{commentId}", method = RequestMethod.PUT)
    public void updateComment(@PathVariable int commentId, @Valid @RequestBody Comments comment) {
        comment.setCommentId(commentId);
        commentsDao.updateComment(comment);
    }

    // Method to delete a comment.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/delete-comment/{commentId}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable int commentId) {
        commentsDao.deleteComment(commentId);
    }
}
