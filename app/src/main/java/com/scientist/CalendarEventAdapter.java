package com.scientist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tdscientist.calendar.R;

import java.util.ArrayList;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 03-Nov-2016
 */

public class CalendarEventAdapter extends BaseAdapter {

    Context context;
    ArrayList<CalendarEventDisplayModel> model;
    Utils utils;

    public CalendarEventAdapter(Context context, ArrayList<CalendarEventDisplayModel> model) {
        this.context = context;
        this.model = model;
        utils = new Utils(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder myHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.events_list_item, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }

        CalendarEventDisplayModel calendarEventDisplayModel = model.get(position);

        myHolder.place.setText(calendarEventDisplayModel.getPlace());
        myHolder.title.setText(calendarEventDisplayModel.getTitle());
        myHolder.time.setText(calendarEventDisplayModel.getTime());
        myHolder.ring.setBackgroundDrawable(calendarEventDisplayModel.isWeekend() ?
                context.getResources().getDrawable(R.drawable.ring_green) :
                context.getResources().getDrawable(R.drawable.ring_blue));
        utils.slideInFromBottom(convertView);
        return convertView;
    }


    private class MyHolder {

        TextView title, place, time, ring;

        public MyHolder(View v) {
            title = (TextView) v.findViewById(R.id.title);
            place = (TextView) v.findViewById(R.id.location);
            time = (TextView) v.findViewById(R.id.time);
            ring = (TextView) v.findViewById(R.id.ring);
        }
    }
}
