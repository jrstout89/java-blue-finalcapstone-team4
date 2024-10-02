package com.techelevator.model;

public class Playdate {

    private int id;
    private String eventTitle;
    private String eventLocation;
    private String eventAddress;
    private int maximumPets;
    private String eventHost;
    private String eventDate;
    private String eventTime;
    private int eventDuration;
    private String eventDescription;
    private String eventImage;

    public Playdate() {
    }

    public Playdate(int id, String eventTitle, String eventLocation, String eventAddress, int maximumPets, String eventHost, String eventDate, String eventTime, int eventDuration, String eventDescription, String eventImage) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventLocation = eventLocation;
        this.eventAddress = eventAddress;
        this.maximumPets = maximumPets;
        this.eventHost = eventHost;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDuration = eventDuration;
        this.eventDescription = eventDescription;
        this.eventImage = eventImage;
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

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }
}
