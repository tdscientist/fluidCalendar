package com;

import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 29-Oct-2016
 */

public class CalendarBounceInterpolator implements Interpolator {

    double mAmplitude = 1;
    double mFrequency = 10;

    public CalendarBounceInterpolator(double mAmplitude, double mFrequency) {
        this.mAmplitude = mAmplitude;
        this.mFrequency = mFrequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, -input / mAmplitude) * Math.cos(mFrequency * input) + 1);
    }
}
