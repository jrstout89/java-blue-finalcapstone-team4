package com.techelevator.dao;

import com.techelevator.model.Forum;

import java.util.List;

public interface ForumDao {
    void createForum(Forum forum);
    Forum getForumById(int id);
    void updateForum(Forum forum);
    void deleteForum(int id);
    List<Forum> getAllForums();
    List<Forum> getForumsByCustomerId(int customerId);
}
