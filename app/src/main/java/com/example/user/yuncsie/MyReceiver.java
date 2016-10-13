package com.example.user.yuncsie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseUser;

public class MyReceiver extends BroadcastReceiver {
    private String[] moviedata = new String[]{"movie1_1", "movie1_2", "movie1_3", "movie2_1", "movie2_2", "movie2_3", "movie3_1", "movie3_2", "movie3_3", "movie4_1",
            "movie4_2", "movie4_3", "movie5_1", "movie5_2", "movie5_3" };
    private ParseUser user = ParseUser.getCurrentUser();
    private int rannum;

    private NotificationManager noMgr;

    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context, "收到", Toast.LENGTH_SHORT).show();
        noMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            do{
                rannum = (int)(Math.random() * 15);
            }while (user.getBoolean(moviedata[rannum]) == true);
       // Toast.makeText(context, Integer.toString(rannum), Toast.LENGTH_SHORT).show();
        user.put(moviedata[rannum], true);
        user.saveInBackground();

        noMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent call = new Intent();
//非必要,可以利用intent傳值
        call.putExtra("notiId", 1);
//建立待處理意圖
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, call, 0);

//指定通知欄位要顯示的圖示
//指定通知出現時要顯示的文字,幾秒後會消失只剩圖示
//何時送出通知,傳入當前時間則立即發出通知
//建立通知物件
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.james2);
        Notification notification = new Notification.Builder(context)
                .setAutoCancel(true)
                .setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.james2)
                .setWhen(System.currentTimeMillis())
                .setTicker("天眼截獲新的紀錄！")
                .setContentTitle("天眼系統通知")
                .setContentText("天眼截獲新的紀錄！請查看")
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pIntent)
                .build();

        noMgr.notify(1, notification);

    }
}
