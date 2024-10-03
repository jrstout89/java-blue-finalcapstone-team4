package com.techelevator.dao;

import com.techelevator.model.Invitation;

import java.util.List;

public interface InvitationDao {

    // get invitation (to_account (to user is the one that is log in))
    List<Invitation> getInvitationToAccount(String username);
    // get invitation (from_account (from user is the one that is log in))
    List<Invitation> getInvitationFromAccount(String username);
    //create invitation
    Invitation createInvitation();
    // update invitation
    Invitation updateInvitation();
    // update status from approved or decline
    Invitation updateStatus();
    // delete invitation

}
