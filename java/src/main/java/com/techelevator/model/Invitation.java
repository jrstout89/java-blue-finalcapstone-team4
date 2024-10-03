package com.techelevator.model;

public class Invitation {

    private int id;
    private int accountFrom;
    private int accountTo;
    private int playdateId;
    private String status;

    public Invitation() {
    }

    public Invitation(int id, int accountFrom, int accountTo, int playdateId,String status) {
        this.id = id;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.playdateId = playdateId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public int getPlaydateId() {
        return playdateId;
    }

    public void setPlaydateId(int playdateId) {
        this.playdateId = playdateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
