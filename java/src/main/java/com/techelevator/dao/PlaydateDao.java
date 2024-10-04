package com.techelevator.dao;

import com.techelevator.model.Playdate;

import java.util.List;

public interface PlaydateDao {

    List<Playdate> getAllPlaydates();

    Playdate getPlaydateById(int id);
    //Get playdates by user's username
    Playdate getPlaydateByUsername(String username);
    Playdate createPlaydate(Playdate playdate);
    boolean  updatePlaydate(Playdate playdate);
    int deletePlaydateById(int id);

}
