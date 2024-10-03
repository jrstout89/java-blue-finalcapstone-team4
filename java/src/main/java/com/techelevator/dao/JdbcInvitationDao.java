package com.techelevator.dao;

import com.techelevator.model.Invitation;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.util.List;

public class JdbcInvitationDao implements InvitationDao{
    @Override
    public List<Invitation> getInvitationToAccount(String username) {
        List<Invitation> invitations = null;
        String sql = "select " +
                "invitation.invitation_id, invitation.account_from, invitation.account_to, invitation.playdate_id, invitation.invitation_status " +
                "from invitations " +
                "join customers on invitations.account_from = customers.customers_id " +
                "join users on customers.user_id = customers.user_id " +
                "where users.username = ?";
        return null;
    }

    @Override
    public List<Invitation> getInvitationFromAccount(String username) {
        return null;
    }

    @Override
    public Invitation createInvitation() {
        return null;
    }

    @Override
    public Invitation updateInvitation() {
        return null;
    }

    @Override
    public Invitation updateStatus() {
        return null;
    }
    private Invitation mapRowToInvitation (SqlRowSet results) {
        Invitation invitation = new Invitation();
        invitation.setId(results.getInt("invitation_id"));
        invitation.setAccountFrom(results.getInt("account_from"));
        invitation.setAccountTo(results.getInt("account_to"));
        invitation.setPlaydateId(results.getInt("playdate_id"));
        invitation.setStatus(results.getString("invitation_status"));
        return invitation;
    }
}
