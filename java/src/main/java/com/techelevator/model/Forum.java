package com.techelevator.model;

import java.time.LocalDate;

public class Forum {

    private int id;
    private int customerId;
    private String forumTitle;
    // Adding content to as the body of each post (i.e title = "Dogs are the best!!" & content = "Share your best dog stories below."
    private String forumContent;
    private LocalDate createdDate;
    private LocalDate updateDate;
    // Changing String comment to List<Comment> comments. Adding another model, Dao, etc. for comments.
    // I changed my mind... I'm going to remove the comments entirely to reduce redundancy. I'm going to just use JOINS later on as needed. Commenting it out for now just in case I change my mind down the line and/or as needed. Removing it from the constructor and commenting out the getter and setter.
//    private List<Comments> comments;

    public Forum() {
    }

    // Added forumContent to the constructor. Commenting it out for now per the above.
    public Forum(int id, int customerId, String forumTitle, String forumContent, String createdDate, String updateDate) {
        this.id = id;
        this.customerId = customerId;
        this.forumTitle = forumTitle;
        this.forumContent = forumContent;
        this.createdDate = LocalDate.parse(createdDate);
        this.updateDate = LocalDate.parse(updateDate);
//        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = LocalDate.parse(createdDate);
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = LocalDate.parse(updateDate);
    }

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComments(String comment) {
//        this.comment = comment;
//    }

    // Added getters and setters for forumContent and comments list.
    public String getForumContent() {
        return forumContent;
    }

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }

//    public List<Comments> getComments() {
//        return comments;
//    }

//    public void setComments(List<Comments> comments) {
//        this.comments = comments;
//    }
}
