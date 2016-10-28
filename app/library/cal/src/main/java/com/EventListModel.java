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


    public EventListModel(boolean year, boolean month, boolean day, String title, String id, int color) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.id = id;
        this.color = color;
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
}
