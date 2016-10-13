package com.example.user.yuncsie;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by user on 2015/11/7.
 */
public class ChatRecevier  extends ParsePushBroadcastReceiver {
    private ActivityManager activityManager;
    String getnotifyid;
    String[] msg;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //分辨目前的activity是哪個---------------------------------------------------------------------------------------
            activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> forGroundActivity = activityManager.getRunningTasks(1);
            ActivityManager.RunningTaskInfo currentActivity; currentActivity = forGroundActivity.get(0);
            String activityName = currentActivity.topActivity.getClassName();
            //---------------------------------------------------------------------------------------------------------------
           // msg[3] = "null";
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            msg = json.getString("alert").toString().split("：");

            ParseUser user = ParseUser.getCurrentUser();
            if(activityName.equals("com.example.user.yuncsie.chat")){

                    Intent passtochat = new Intent();
                    passtochat.setAction("Hello");
                    passtochat.putExtra("title", msg[1]);
                    passtochat.putExtra("name", msg[2]);
                    passtochat.putExtra("msg", msg[3]);
                    context.sendBroadcast(passtochat);
            }
            else{
                if(!user.getString("getachievement").equals("0")){
                    Intent passtonotify = new Intent();
                    passtonotify.setAction("gonotify");
                    passtonotify.putExtra("notifyid", msg[0]);
                    passtonotify.putExtra("name",msg[2]);
                    passtonotify.putExtra("msg",msg[3]);
                    context.sendBroadcast(passtonotify);
                } else{
                    Intent passtonotify = new Intent();
                    passtonotify.setAction("gonotify");
                    passtonotify.putExtra("notifyid", msg[0]);
                    passtonotify.putExtra("name",msg[1]);
                    passtonotify.putExtra("msg",msg[2]);
                    context.sendBroadcast(passtonotify);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}

