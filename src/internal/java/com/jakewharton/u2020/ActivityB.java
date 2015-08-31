package com.jakewharton.u2020;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by dpozega on 8/22/15.
 */
public class ActivityB extends AppCompatActivity {
    private static ActivityB ourInstance = new ActivityB();

    public static ActivityB getInstance() {
        return ourInstance;
    }

    private ActivityB() {
    }
}
