package com.techelevator.model;

public class Forum {

    private int id;
    private int customerId;
    private String forumTitle;
    private String createdDate;
    private String updateDate;
    private String comment;

    public Forum() {
    }

    public Forum(int id, int customerId, String forumTitle, String createdDate, String updateDate, String comment) {
        this.id = id;
        this.customerId = customerId;
        this.forumTitle = forumTitle;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.comment = comment;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
