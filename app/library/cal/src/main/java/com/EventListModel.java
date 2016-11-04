package com;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 26-Oct-2016
 */

public class EventListModel {

    private boolean year;
    private boolean month;
    private boolean day;
    private String title;
    private String id;
    private int color;
    private String place;
    private String time;


    public EventListModel(boolean year, boolean month, boolean day, String title, String id, int color,
                          String place, String time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.id = id;
        this.color = color;
        this.place = place;
        this.time = time;
    }

    public boolean getYear() {
        return year;
    }

    public boolean getMonth() {
        return month;
    }

    public boolean getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }
}
