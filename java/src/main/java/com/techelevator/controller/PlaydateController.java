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
    public List<Playdate> getAllPlaydates(Principal principal) {
        if(principal==null){
            return playdateDao.getAllPlaydates();
        }
        Customers customers=userDao.getCustomer(principal.getName());

        return playdateDao.getAllPlaydates(customers.getId());
    }

    // Get a playdate by ID.
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public Playdate getPlaydateById(@PathVariable int id) {
        return playdateDao.getPlaydateById(id);
    }

    @GetMapping(path =  "/personal-events")
    public List<Playdate> getPlayDateByUsername(Principal user) {
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
    @RequestMapping(path="/playdates/{petId}", method = RequestMethod.GET)
    public List<Playdate> getPlaydatesByPetId(@PathVariable int petId){
        return playdateDao.getPlaydateByPetId(petId);
    }
    @RequestMapping(path = "/{playdateId}/pets/{petId}", method = RequestMethod.DELETE)
    public void removePet(@PathVariable int playdateId, @PathVariable int petId){
        playdateDao.removePetFromPlaydate(playdateId, petId);
    }
}
