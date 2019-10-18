package com.example.communityhero;

import com.parse.Parse;
import com.parse.ParseInstallation;

import android.app.Application;

//DON'T MAKE CHANGES!
public class ParseServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.app_id))
                .clientKey(getString(R.string.client_key))
                .server(getString(R.string.server_url))
                .build()
        );

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}