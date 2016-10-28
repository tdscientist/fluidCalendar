package com.scientist;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.EventListModel;
import com.p_v.flexiblecalendar.FlexibleCalendarView;
import com.p_v.flexiblecalendar.entity.CalendarEvent;
import com.tdscientist.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 28-Oct-2016
 */


public class CalendarActivityFragment extends Fragment implements FlexibleCalendarView.OnMonthChangeListener,
        FlexibleCalendarView.OnDateClickListener, View.OnClickListener {

    private FlexibleCalendarView calendarView;
    DigitalClock time;
    FloatingActionButton floatingActionButton;


    public CalendarActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        Configuration configuration = getResources().getConfiguration();
        if (configuration.screenWidthDp >= 550) {
            view = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ?
                    inflater.inflate(R.layout.fragment_calendar_landscape, container, false) :
                    inflater.inflate(R.layout.fragment_calendar, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_calendar, container, false);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        time = (DigitalClock) view.findViewById(R.id.time);
        time.setTypeface(FontCache.getTypeface(getActivity(), "fonts/ds_digib.ttf"));
        calendarView = (FlexibleCalendarView) view.findViewById(R.id.calendar_view);

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                    .LayoutParams(dpToPx(configuration.screenWidthDp / 2), ViewGroup.LayoutParams.MATCH_PARENT);
            calendarView.setLayoutParams(layoutParams);
        }

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        calendarView.setOnMonthChangeListener(this);
        calendarView.setOnDateClickListener(this);
        calendarView.setEventDataProvider(new FlexibleCalendarView.EventDataProvider() {
            @Override
            public List<CalendarEvent> getEventsForTheDay(int year, int month, int day) {

                ArrayList<EventListModel> eventListModel = new ArrayList<>();
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 12, "my title 0", "id0", android.R.color.holo_blue_light));
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 12, "my title 1", "id1", android.R.color.holo_purple));
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 16, "my title 2", "id2", android.R.color.holo_blue_light));
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 18, "my title 3", "id3", android.R.color.holo_blue_light));
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 21, "my title 4", "id4", android.R.color.holo_blue_light));
                eventListModel.add(new EventListModel(year == 2016, month == 9, day == 30, "my title 5", "id5", android.R.color.holo_blue_light));

                return addEventsToCalendar(eventListModel);
            }
        });


        return view;
    }


    private List<CalendarEvent> addEventsToCalendar(ArrayList<EventListModel> eventListModel) {
        List<CalendarEvent> eventColors = new ArrayList<>();
        for (int i = 0; i < eventListModel.size(); i++) {
            if (eventListModel.get(i).getYear() && eventListModel.get(i).getMonth() &&
                    eventListModel.get(i).getDay()) {
                eventColors.add(new CalendarEvent(eventListModel.get(i).getColor(),
                        eventListModel.get(i).getTitle(), eventListModel.get(i).getId()));

            }
        }
        if (eventListModel.size() > 0) {
            return eventColors;
        } else {
            return null;
        }

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onMonthChange(int year, int month, int direction) {

    }

    @Override
    public void onDateClick(int year, int month, int day) {

        if (calendarView.getEventsForTheDay(year, month, day).size() > 0) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            Toast.makeText(getActivity(), cal.getTime().toString() + " Clicked", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < calendarView.getEventsForTheDay(year, month, day).size(); i++) {
                //// TODO: 10/26/16  get a list of your events here
                Log.i("DEBUG==>", calendarView.getEventsForTheDay(year, month, day).get(i).getEventId());
            }

        }


    }

    @Override
    public void onClick(View v) {
        if (v == floatingActionButton) {
            //todo create your events here
            Toast.makeText(getActivity(), "EVENT CREATED", Toast.LENGTH_SHORT).show();
        }
    }

    private int dpToPx(int dp) {
        float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


}
