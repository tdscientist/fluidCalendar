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

    public CalendarEvent(int color, String title, String id) {
        this.color = color;
        this.title = title;
        this.id = id;
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
}
