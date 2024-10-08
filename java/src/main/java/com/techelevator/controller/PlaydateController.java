package com.techelevator.controller;

import com.techelevator.dao.PlaydateDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Customers;
import com.techelevator.model.Playdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PlaydateController {

    @Autowired
    private PlaydateDao playdateDao;
    @Autowired
    private UserDao userDao;

    // Get all playdates.
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Playdate> getAllPlaydates() {
        return playdateDao.getAllPlaydates();
    }

    // Get a playdate by ID.
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public Playdate getPlaydateById(@PathVariable int id) {
        return playdateDao.getPlaydateById(id);
    }

    @GetMapping(path =  "/personal-events")
    public Playdate getPlayDateByUsername(Principal user) {
        String username = user.getName();
        return playdateDao.getPlaydateByUsername(username);
    }
    // Create a new playdate.
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/events", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Playdate createPlaydate(@RequestBody Playdate playdate, Principal principal) {
        Customers customers=userDao.getCustomer(principal.getName());
        playdate.setEventHost(customers.getId());
        return playdateDao.createPlaydate(playdate);
    }

    @RequestMapping(path="/update-event", method = RequestMethod.PUT)
    public boolean updatePlaydate(@RequestBody Playdate playdate){
        return playdateDao.updatePlaydate(playdate);
    }

    @RequestMapping(path = "/events/{id}", method = RequestMethod.DELETE)
    public void deletePlaydate(@PathVariable int id){
        playdateDao.deletePlaydateById(id);
    }








    // Accept a playdate by ID.
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(path = "/events/{id}/accept", method = RequestMethod.PUT)
//    public Playdate acceptPlaydate(@PathVariable int id) {
//        return playdateDao.acceptPlaydate(id);
//    }

    // Decline a playdate by ID.
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(path = "/events/{id}/decline", method = RequestMethod.PUT)
//    public Playdate declinePlaydate(@PathVariable int id) {
//        return playdateDao.declinePlaydate(id);
//    }
}
