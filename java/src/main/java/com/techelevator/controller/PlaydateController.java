package com.techelevator.controller;

import com.techelevator.dao.PlaydateDao;
import com.techelevator.model.Playdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class PlaydateController {

    @Autowired
    private PlaydateDao playdateDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Playdate> getAllPlaydates() {
        return playdateDao.getAllPlaydates();
    }

}
