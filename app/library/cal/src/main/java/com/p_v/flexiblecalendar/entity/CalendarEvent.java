package com.p_v.flexiblecalendar.entity;

/**
 * @author p-v
 */


/**
 * Rewritten
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 26-Oct-2016
 */


public class CalendarEvent implements Event {

    private int color;
    private String title;
    private String id;
    private String place;
    private String time;


    public CalendarEvent(int color, String title, String id, String place, String time) {
        this.color = color;
        this.title = title;
        this.id = id;
        this.place = place;
        this.time = time;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public String getEventTitle() {
        return title;
    }

    @Override
    public String getEventId() {
        return id;
    }

    @Override
    public String getEventPlace() {
        return place;
    }

    @Override
    public String getEventTime() {
        return time;
    }


}
