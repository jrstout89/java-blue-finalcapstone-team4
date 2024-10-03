package com.techelevator.dao;

import com.techelevator.model.Playdate;

import java.util.List;

public interface PlaydateDao {

    List<Playdate> getAllPlaydates();

    Playdate getPlaydateById(int id);

    Playdate createPlaydate(Playdate playdate);

    Playdate acceptPlaydate(int id);

    Playdate declinePlaydate(int id);

}
