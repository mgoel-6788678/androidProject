package com.example.evento;

public class EventsClass {
    String EventDate, EventTime, EventDescription, EventLocation;

    public EventsClass(){

    }

    public EventsClass(String eventDate, String eventTime, String eventDescription, String eventLocation) {
        EventDate = eventDate;
        EventTime = eventTime;
        EventDescription = eventDescription;
        EventLocation = eventLocation;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public String getEventTime() {
        return EventTime;
    }

    public void setEventTime(String eventTime) {
        EventTime = eventTime;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public void setEventLocation(String eventLocation) {
        EventLocation = eventLocation;
    }
}
