package com.techelevator.model;

public class PlaydatePets {

    private int id;
    private int playdateId;
    private int petId;

    public PlaydatePets() {
    }

    public PlaydatePets(int id, int playdateId, int petId) {
        this.id = id;
        this.playdateId = playdateId;
        this.petId = petId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaydateId() {
        return playdateId;
    }

    public void setPlaydateId(int playdateId) {
        this.playdateId = playdateId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
