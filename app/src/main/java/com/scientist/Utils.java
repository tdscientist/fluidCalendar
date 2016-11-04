package com.scientist;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tdscientist.calendar.R;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 04-Nov-2016
 */

public class Utils {

    Context mContext;

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    public void slideInFromTop(View v) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_from_top);
        v.setAnimation(animation);
        v.animate();
        animation.start();
    }

    public void slideInFromBottom(View v) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_from_bottom);
        v.setAnimation(animation);
        v.animate();
        animation.start();
    }


    public String getReadableDayOfWeek(int day) {
        String d = "";
        switch (day) {
            case 1:
                d = "Sunday";
                break;
            case 2:
                d = "Monday";
                break;
            case 3:
                d = "Tuesday";
                break;
            case 4:
                d = "Wednesday";
                break;
            case 5:
                d = "Thursday";
                break;
            case 6:
                d = "Friday";
                break;
            case 7:
                d = "Saturday";
                break;
        }
        return d;
    }

    public int dpToPx(int dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
