package com.example.user.yuncsie;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by user on 2015/12/21.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);     //啟用本機資料
        Parse.initialize(this, "81UEK9RRBxgsCXAaWEdtPvOfD1oMYeUPBUdAjQF3", "FoSpyUAVQTnj2XvWOzJFld1fQt23ST8oqGgkI9U3");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}