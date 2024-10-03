package com.techelevator.dao;

import com.techelevator.model.Invitation;

import java.util.List;

public interface InvitationDao {

    // get invitation (to_account (to user is the one that is log in))
    List<Invitation> getInvitationToAccount(String username);

    // get invitation (from_account (from user is the one that is log in))
    List<Invitation> getInvitationFromAccount(String username);

    //get invitation by invitation_id
    Invitation getInvitationById(int id);

    //create invitation
    Invitation createInvitation(Invitation createInvitation);

    // update invitation
    Invitation updateInvitation(Invitation invitation);

    // update status from approved or decline
    Invitation updateStatus(Invitation invitation);

    // delete invitation

}
