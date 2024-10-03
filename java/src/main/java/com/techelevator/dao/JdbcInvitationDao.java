package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Invitation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.util.List;

public class JdbcInvitationDao implements InvitationDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcInvitationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Invitation> getInvitationToAccount(String username) {
        List<Invitation> invitations = null;
        String sql = "select " +
                "invitation.invitation_id, invitation.account_from, invitation.account_to, invitation.playdate_id, invitation.invitation_status " +
                "from invitation " +
                "join customers on invitation.account_from = customers.customers_id " +
                "join users on customers.user_id = customers.user_id " +
                "where users.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                invitations.add(mapRowToInvitation(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return invitations;
    }

    @Override
    public List<Invitation> getInvitationFromAccount(String username) {
        List<Invitation> invitations = null;
        String sql = "select " +
                "invitation.invitation_id, invitation.account_from, invitation.account_to, invitation.playdate_id, invitation.invitation_status " +
                "from invitation " +
                "join customers on invitation.account_to = customers.customers_id " +
                "join users on customers.user_id = customers.user_id " +
                "where users.username = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                invitations.add(mapRowToInvitation(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return invitations;
    }

    @Override
    public Invitation getInvitationById(int id) {
        Invitation invitation = null;
        String sql = "select * from invitation " +
                "where invitation_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                invitation =  mapRowToInvitation(results);
            } else {
                throw new DaoException("Invitation not found.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
        return invitation;
    }

    @Override
    public Invitation createInvitation(Invitation createInvitation) {
        Invitation invitation = null;
        String sql = "INSERT INTO invitation (account_to,playdate_id) " +
                "VALUES (?,?) RETURNING invitation_id";
        try {
            int newInvitationId = jdbcTemplate.queryForObject(sql, int.class,createInvitation.getAccountTo(),createInvitation.getPlaydateId());

            invitation = getInvitationById(newInvitationId);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return invitation;
    }

    @Override
    public Invitation updateInvitation(Invitation invitation) {
        Invitation updateInvitation = null;
        String sql = "UPDATE playdate SET playdate_id = ? WHERE invitation_id = ?";
        int numOfRows = jdbcTemplate.update(sql,invitation.getPlaydateId(),invitation.getId());
        try {
            if (numOfRows == 1) {
                updateInvitation = getInvitationById(invitation.getId());
            } else {
                throw new DaoException("Invitation not found", new Exception());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
        return updateInvitation;
    }

    @Override
    public Invitation updateStatus(Invitation invitation) {
        Invitation updateStatus = null;
        String sql = "UPDATE playdate SET invitation_status = ? WHERE invitation_id = ?";
        int numOfRows = jdbcTemplate.update(sql,invitation.getStatus(),invitation.getId());
        try {
            if (numOfRows == 1) {
                updateStatus = getInvitationById(invitation.getId());
            } else {
                throw new DaoException("Invitation not found", new Exception());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        }
        return updateStatus;
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
