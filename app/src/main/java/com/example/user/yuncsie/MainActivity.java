package com.example.user.yuncsie;


import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.yuncsie.secrettextview.SecretTextView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int CUSTOM_PROGRESSDIALOG_ID = 0;
    private final Handler mhandler = new Handler();
    private NotificationManager noMgr;
    private Intent intent = new Intent();
    SecretTextView secretTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------------------------------------------
        final Button login = (Button) findViewById(R.id.button);
        final TextView logintext = (TextView)findViewById(R.id.textView);
        final EditText account = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText)findViewById(R.id.editText2);
        secretTextView = (SecretTextView) findViewById(R.id.textView4);


        secretTextView.setIsVisible(true);
        Timer backgroundtimer = new Timer();
        backgroundtimer.schedule(backgroundtask, 0, 3000);
        //---------------------------------------------------------
//設定字型
        Typeface face = Typeface.createFromAsset(getAssets(),"yyy.TTF");
        logintext.setTypeface(face);
        secretTextView.setTypeface(face);
        account.setTypeface(face);
        password.setTypeface(face);
        login.setOnClickListener(this);

    }
//Loading---------------------------------------------------------------------------------------------
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch(id) {
            case CUSTOM_PROGRESSDIALOG_ID:
                dialog = new Dialog(MainActivity.this, R.style.progressDialog);
                dialog.setContentView(R.layout.progressdialog);
                dialog.setCancelable(false);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.alpha = 0.8f;
                dialog.getWindow().setAttributes(params);
                break;
        }
        return dialog;
    }

    //onCreate結束-----------------------------------------------------------------------------
    //onDestroy-------------------------------------------------------
    @Override
    protected void onDestroy(){
        super.onDestroy();

    }
    //Click事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:           //登入→進入home
                showDialog(CUSTOM_PROGRESSDIALOG_ID);
                final EditText account_text = (EditText) this.findViewById(R.id.editText);
                final EditText password_text = (EditText) this.findViewById(R.id.editText2);
                login(account_text.getText().toString().trim(), password_text.getText().toString().trim());
                break;



        }
    }

//登入-------------------------------------------------------------------------
    public void login(final String username, final String password) {
        if (netstate())
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                public void done(final ParseUser user, ParseException e) {

                    if (username.equals("")) {
                        removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                        Toast.makeText(MainActivity.this, "請輸入帳號", Toast.LENGTH_SHORT).show();
                        return;

                    } else if (password.equals("")) {
                        removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                        Toast.makeText(MainActivity.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (user != null ) {
                        if (user.getString("used").equals("1")){
                            Log.i("loging", "1");
                            removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                            Toast.makeText(MainActivity.this, "還沒開始呦~敬請期待！", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Log.i("loging","0");
                        Toast.makeText(MainActivity.this, "登入成功，身分為" + user.getString("Name"), Toast.LENGTH_LONG).show();
                        //user.put("used", 1);
                        backgroundtask.cancel();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, home.class);
                        startActivity(intent);
                        removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                        MainActivity.this.finish();
                    }
                    else{
                        removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                        Toast.makeText(MainActivity.this, "帳號密碼錯誤，請重試", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        else {
            removeDialog(CUSTOM_PROGRESSDIALOG_ID);
            Toast.makeText(MainActivity.this, "偵測不到網路", Toast.LENGTH_SHORT).show();
        }


    }


    //Task--------------------------------------------------------------------------
    private TimerTask backgroundtask = new TimerTask() {
        @Override
        public void run() {
            mhandler.post(backgroundset);
        }
    };
    //Runnable------------------------------------------------------------------------
    private Runnable backgroundset = new Runnable() {
        @Override
        public void run() {
            secretTextView = (SecretTextView) findViewById(R.id.textView4);
            secretTextView.toggle();
        }
    };


//偵測網路狀態-------------------------------------------------------------------
    private boolean netstate() {
        boolean result = false;
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            result = false;
        } else {
            if (!info.isAvailable()) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }
 //實體退回建-----------------------------------------------------------------------------------------------------------
    private static Boolean isExit = false;
    private static Boolean hasTask = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;
                Toast.makeText(this, "再按一次後退鍵退出應用程式", Toast.LENGTH_SHORT).show();
                if(!hasTask) {
                    tExit.schedule(task, 2000);
                }
            }else {
                finish();
            }
        }
        return false;
    }
}
