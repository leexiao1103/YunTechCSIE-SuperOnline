package com.example.user.yuncsie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2015/12/22.
 */
public class skilllayout extends AppCompatActivity {
    private Handler mhandler = new Handler();

    private ParseUser user = ParseUser.getCurrentUser();
    private Intent intent;
    private Timer timer;
    private Timer timer2;
    private Timer timer3;

    int secd = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skill);
        ImageView skillimage = (ImageView) findViewById(R.id.imageView3);
        Button goback = (Button) findViewById(R.id.button13);
        intent = new Intent();
        intent.setClass(skilllayout.this, home.class);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("goback", "click");


                startActivity(intent);
                skilllayout.this.finish();
            }
        });

        switch (user.getString("agancy")){
            case "東廠":
                skillimage.setImageResource(R.drawable.chinese);
                break;
            case "OO7":
                skillimage.setImageResource(R.drawable.my_friend);
                break;
            case "小":
                skillimage.setImageResource(R.drawable.getmonster);
                break;
            case "IMF":
                skillimage.setImageResource(R.drawable.gameboy);
                break;
            case "世界政府":
                skillimage.setImageResource(R.drawable.hoandgon);
                break;
        }


        switch (user.getInt("skill")) {
            case 0:
                timer = new Timer();
                timer.schedule(backgroundtask, 0, 1000);
                Log.i("timer", "timergo");

                break;
            case 1:
                timer2 = new Timer();
                timer2.schedule(backgroundtask2, 0, 1000);
                Log.i("timer2", "timer2go");

                break;
            case 2:
                timer3 = new Timer();
                timer3.schedule(backgroundtask3, 0, 1000);
                Log.i("timer3", "timer3go");

                break;
        }
    }



    private Runnable backgroundset = new Runnable() {
        @Override
        public void run() {
            secd = secd - 1;
            TextView sec = (TextView) findViewById(R.id.textView12);
            if(secd != 0){
                sec.setText(Integer.toString(secd));
            }else{
                startActivity(intent);
                skilllayout.this.finish();
            }
        }
    };

    private TimerTask backgroundtask = new TimerTask() {
        @Override
        public void run() {
            mhandler.post(backgroundset);
        }
    };
    private TimerTask backgroundtask2 = new TimerTask() {
        @Override
        public void run() {
            mhandler.post(backgroundset);
        }
    };
    private TimerTask backgroundtask3 = new TimerTask() {
        @Override
        public void run() {
            mhandler.post(backgroundset);
        }
    };
}

