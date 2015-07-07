package com.fourstrategery.fourstratdroid;

import android.app.Application;

import com.fourstrategery.fourstratdroid.util.VolleyUtil;

/**
 * Created by REC on 7/6/2015.
 */
public class FourStatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }


    private void init() {
        VolleyUtil.init(this);
    }
}
