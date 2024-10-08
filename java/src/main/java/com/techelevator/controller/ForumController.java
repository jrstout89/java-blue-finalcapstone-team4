package com.techelevator.controller;

import com.techelevator.dao.ForumDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Customers;
import com.techelevator.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ForumController {
    @Autowired
    private ForumDao forumDao;
    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/add-forum", method = RequestMethod.POST)
    public void addForum(@Valid @RequestBody Forum forum, Principal principal) {
        Customers customer = userDao.getCustomer(principal.getName());
        forum.setCustomerId(customer.getId());
        forumDao.createForum(forum);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/update-forum", method = RequestMethod.PUT)
    public void updateForum(@Valid @RequestBody Forum forum){
        forumDao.updateForum(forum);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/forums/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        forumDao.deleteForum(id);
    }
    @RequestMapping(path = "/forum/{id}", method = RequestMethod.GET)
    public Forum getForumById(@PathVariable int id){
        return forumDao.getForumById(id);
    }
    @RequestMapping(path = "/forums", method = RequestMethod.GET)
    public List<Forum> getAllForums(){
        return forumDao.getAllForums();
    }
    @RequestMapping(path="/{id}/forums", method = RequestMethod.GET)
    public List<Forum> getForumsByCustomerId(@PathVariable int id, Principal principal){
        Customers customers = userDao.getCustomer(principal.getName());
        return forumDao.getForumsByCustomerId(customers.getId());

    }


}
