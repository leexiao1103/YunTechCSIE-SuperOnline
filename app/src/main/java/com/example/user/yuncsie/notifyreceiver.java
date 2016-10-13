package com.example.user.yuncsie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by user on 2015/12/3.
 */
public class notifyreceiver extends BroadcastReceiver {
    private NotificationManager NM;
    private Notification notification;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle getname = intent.getExtras();
        int notifyID = getname.getInt("notifyid");
       // Toast.makeText(context,Integer.toString(notifyID),Toast.LENGTH_LONG).show();



            Intent call = new Intent(context, chat.class);
            Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.james2);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, call, PendingIntent.FLAG_UPDATE_CURRENT);

            NM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notification = new Notification.Builder(context)
                    .setAutoCancel(true)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.james2)
                    .setWhen(System.currentTimeMillis())
                    .setTicker("聊天室收到新訊息")
                    .setContentTitle("聊天室通知")
                    .setContentText("聊天室收到新訊息")
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pIntent)
                    .build();


            NM.notify(notifyID, notification);


    }


}
