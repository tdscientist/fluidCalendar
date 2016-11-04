package com.scientist;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    RelativeLayout mother;
    TextView tappedDate, tappedDay, addNewEvent;
    int thisWeek, today;
    ListView listView;
    CalendarEventAdapter calendarEventAdapter;
    ArrayList<CalendarEventDisplayModel> eventDisplayModel;
    Utils utils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        utils = new Utils(getActivity());
        View view;
       /* Configuration configuration = getResources().getConfiguration();
        if (configuration.screenWidthDp >= 550) {
            view = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ?
                    inflater.inflate(R.layout.fragment_calendar_landscape, container, false) :
                    inflater.inflate(R.layout.fragment_calendar, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_calendar, container, false);
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }*/

        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        calendarView = (FlexibleCalendarView) view.findViewById(R.id.calendar_view);

        /*if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                    .LayoutParams(utils.dpToPx(configuration.screenWidthDp / 2), ViewGroup.LayoutParams.MATCH_PARENT);
            calendarView.setLayoutParams(layoutParams);
        }*/

        calendarView.setOnMonthChangeListener(this);
        calendarView.setOnDateClickListener(this);
        calendarView.setEventDataProvider(new FlexibleCalendarView.EventDataProvider() {
            @Override
            public List<CalendarEvent> getEventsForTheDay(int year, int month, int day) {

                ArrayList<EventListModel> eventListModel = new ArrayList<>();
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 12,
                        "my title 0", "id0", android.R.color.holo_blue_light, "Dierra City Center", "9:00 -11:30"));
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 12,
                        "my title 1", "id1", android.R.color.holo_purple, "Dierra City Center", "9:00 -11:30"));
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 16,
                        "my title 2", "id2", android.R.color.holo_blue_light, "Dierra City Center", "9:00 -11:30"));
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 18,
                        "my title 3", "id3", android.R.color.holo_blue_light, "Dierra City Center", "9:00 -11:30"));
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 21,
                        "my title 4", "id4", android.R.color.holo_blue_light, "Dierra City Center", "9:00 -11:30"));
                eventListModel.add(new EventListModel(year == 2016, month == 10, day == 30,
                        "my title 5", "id5", android.R.color.holo_blue_light, "Dierra City Center", "9:00 -11:30"));

                return addEventsToCalendar(eventListModel);
            }
        });

        mother = (RelativeLayout) view.findViewById(R.id.mother);
        tappedDate = (TextView) view.findViewById(R.id.date);
        tappedDay = (TextView) view.findViewById(R.id.day);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        today = day;
        thisWeek = cal.get(Calendar.WEEK_OF_YEAR);
        setTappedDateOnText(year, month, day);
        setTappedDayOnText(day, cal);

        eventDisplayModel = new ArrayList<>();
        calendarEventAdapter = new CalendarEventAdapter(getContext(), eventDisplayModel);

        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(calendarEventAdapter);

        addNewEvent = (TextView) view.findViewById(R.id.add);
        addNewEvent.setOnClickListener(this);
        return view;
    }


    private List<CalendarEvent> addEventsToCalendar(ArrayList<EventListModel> eventListModel) {
        List<CalendarEvent> eventColors = new ArrayList<>();
        for (int i = 0; i < eventListModel.size(); i++) {
            if (eventListModel.get(i).getYear() && eventListModel.get(i).getMonth() &&
                    eventListModel.get(i).getDay()) {
                eventColors.add(new CalendarEvent(eventListModel.get(i).getColor(),
                        eventListModel.get(i).getTitle(), eventListModel.get(i).getId(),
                        eventListModel.get(i).getPlace(), eventListModel.get(i).getTime()));

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

        eventDisplayModel.clear();

        if (calendarView.getEventsForTheDay(year, month, day).size() > 0) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            //Toast.makeText(getActivity(), cal.getTime().toString() + " Clicked", Toast.LENGTH_SHORT).show();
            boolean isWeekend = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;

            for (int i = 0; i < calendarView.getEventsForTheDay(year, month, day).size(); i++) {
                eventDisplayModel.add(new CalendarEventDisplayModel(calendarView.getEventsForTheDay(year, month, day).get(i).getEventTitle(),
                        calendarView.getEventsForTheDay(year, month, day).get(i).getEventPlace(),
                        calendarView.getEventsForTheDay(year, month, day).get(i).getEventTime(), isWeekend));
                //// TODO: 10/26/16  get a list of your events here
                //  Log.i("DEBUG==>", calendarView.getEventsForTheDay(year, month, day).get(i).getEventId());
            }

        }

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            mother.setBackgroundDrawable(getResources().getDrawable(R.color.calendar_selected_date_green));
        } else {
            mother.setBackgroundDrawable(getResources().getDrawable(R.color.calendar_selected_date_blue));
        }

        setTappedDateOnText(year, month, day);
        setTappedDayOnText(day, cal);

        calendarEventAdapter.notifyDataSetChanged();

    }

    private void setTappedDateOnText(int year, int month, int day) {
        tappedDate.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
        utils.slideInFromTop(tappedDate);
    }

    private void setTappedDayOnText(int day, Calendar c) {
        int selectedWeek = c.get(Calendar.WEEK_OF_YEAR);
        if (selectedWeek == thisWeek) {
            if (today == day) {
                tappedDay.setText("Today");
            } else if (day == (today - 1)) {
                tappedDay.setText("Yesterday");
            } else if (day == (today + 1)) {
                tappedDay.setText("Tomorrow");
            } else {
                tappedDay.setText("This " + utils.getReadableDayOfWeek(c.get(Calendar.DAY_OF_WEEK)));
            }
        } else if (selectedWeek == (thisWeek - 1)) {
            tappedDay.setText("Last " + utils.getReadableDayOfWeek(c.get(Calendar.DAY_OF_WEEK)));
        } else if (selectedWeek == (thisWeek + 1)) {
            tappedDay.setText("Next " + utils.getReadableDayOfWeek(c.get(Calendar.DAY_OF_WEEK)));
        } else {
            tappedDay.setText(utils.getReadableDayOfWeek(c.get(Calendar.DAY_OF_WEEK)));
        }
        utils.slideInFromTop(tappedDay);
    }

    @Override
    public void onClick(View v) {
        if (v == addNewEvent) {
            //// TODO: 11/4/16  
        }
    }

 /*   public static class NewEvent extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View fragView = inflater.inflate(R.layout.event_add, container, false);

            return fragView;
        }
    }*/

}
