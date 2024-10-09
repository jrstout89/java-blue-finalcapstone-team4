package com.techelevator.model;

public class Comments {

    // Primary key for the table.
    private int commentId;
    // References the forum that this comment belongs to.
    private int forumId;
    // References the customerId associated with the person posting the comment.
    private int customerId;
    // References the body of the content within the posted comment.
    private String commentContent;
    // References the date/time the comment was made.
    private String createdDate;
    // Connecting the username in the sql statements
    private String username;

    public Comments() {
    }

    // Just a constructor.
    public Comments(int commentId, int forumId, int customerId, String commentContent, String createdDate, String username) {
        this.commentId = commentId;
        this.forumId = forumId;
        this.customerId = customerId;
        this.commentContent = commentContent;
        this.createdDate = createdDate;
        this.username = username;
    }

    // Just all of the getters/setters.
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
