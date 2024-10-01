package com.techelevator.model;

public class Playdate {

    private int id;
    private String eventLocation;
    private int maximumPets;
    private String eventHost;
    private String eventDate;
    private String eventTime;
    private int eventDuration;

    public Playdate() {
    }

    public Playdate(int id, String eventLocation, int maximumPets, String eventHost, String eventDate, String eventTime, int eventDuration) {
        this.id = id;
        this.eventLocation = eventLocation;
        this.maximumPets = maximumPets;
        this.eventHost = eventHost;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDuration = eventDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public int getMaximumPets() {
        return maximumPets;
    }

    public void setMaximumPets(int maximumPets) {
        this.maximumPets = maximumPets;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }
}
