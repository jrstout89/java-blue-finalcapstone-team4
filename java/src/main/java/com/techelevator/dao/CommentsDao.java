package com.techelevator.dao;

import com.techelevator.model.Comments;

import java.util.List;

public interface CommentsDao {

    Comments addComment(Comments comment);
    List<Comments> getCommentsForForum(int forumId);
    Comments getCommentById(int commentId);
    boolean updateComment(Comments comment);
    boolean deleteComment(int commentId);
}
