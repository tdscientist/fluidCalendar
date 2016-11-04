package com.scientist;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 03-Nov-2016
 */

public class CalendarEventDisplayModel {


    private String title;
    private String place;
    private String time;
    private boolean isWeekend;


    public CalendarEventDisplayModel(String title, String place, String time, boolean isWeekend) {
        this.title = title;
        this.place = place;
        this.time = time;
        this.isWeekend = isWeekend;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
