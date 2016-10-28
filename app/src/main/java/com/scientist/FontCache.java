package com.scientist;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Copyright (c) 2016 Adediji Adeyinka(tdscientist)
 * All rights reserved
 * Created on 26-Oct-2016
 */

public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(Context context, String fontPath) {
        Typeface typeface = fontCache.get(fontPath);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontPath, typeface);
        }

        return typeface;
    }
}