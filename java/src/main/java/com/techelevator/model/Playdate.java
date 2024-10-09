package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Playdate {

    private int id;
    private String eventTitle;
    private String eventLocation;
    private String eventAddress;
    private int maximumPets;
    private int eventHost;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private int eventDuration;
    private String eventDescription;
    private String eventImage;

    public Playdate() {
    }

    public Playdate(int id, String eventTitle, String eventLocation, String eventAddress, int maximumPets, int eventHost, LocalDate eventDate, LocalTime eventTime, int eventDuration, String eventDescription, String eventImage) {
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

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public int getMaximumPets() {
        return maximumPets;
    }

    public void setMaximumPets(int maximumPets) {
        this.maximumPets = maximumPets;
    }

    public int getEventHost() {
        return eventHost;
    }

    public void setEventHost(int eventHost) {
        this.eventHost = eventHost;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
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
}
