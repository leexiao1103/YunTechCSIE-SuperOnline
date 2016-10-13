package com.example.user.yuncsie;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.yuncsie.ViewPager.godeye;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by user on 2015/11/3.
 */
public class chat extends AppCompatActivity implements View.OnClickListener, OnMenuItemClickListener {
    static final int CUSTOM_PROGRESSDIALOG_ID = 0;

    private ParseUser user = ParseUser.getCurrentUser();
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private Myreceiver chatreceiver;
    private ScrollView scroll;
    private String achievement;
    Intent intent = new Intent();
    private String test;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        final Button sendmsg = (Button) findViewById(R.id.button3);
        scroll = (ScrollView) findViewById(R.id.scrollView);
        sendmsg.setOnClickListener(this);

        achievement = user.getString("achievement");
        chatreceiver = new Myreceiver();
        IntentFilter intentFilter = new IntentFilter();     //創建一個IntentFilte物件
        intentFilter.addAction("Hello");    //加入Action的辨識字串
        this.registerReceiver(chatreceiver, intentFilter);      //註冊我們創建的BroadcastReceiver
        showDialog(CUSTOM_PROGRESSDIALOG_ID);
        getchatData();


        fragmentManager = getSupportFragmentManager();      //選單設定咚咚
        initToolbar();
        initMenuFragment();

        sendScroll();

    }
//onCreate結束-------------------------------------------------------------------------------------
private void sendScroll(){
    final Handler handler = new Handler();
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {Thread.sleep(100);} catch (InterruptedException e) {}
            handler.post(new Runnable() {
                @Override
                public void run() {
                    scroll.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }).start();
}
    //點擊事件-------------------------------------------------------------------------
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:      //傳送聊天
                final ParseObject chatData = new ParseObject("Chat");
                final ParseUser user = ParseUser.getCurrentUser();
                final EditText msg = (EditText) findViewById(R.id.editText3);
                final TextView chatblock = (TextView) findViewById(R.id.textView3);
                ParsePush push = new ParsePush();
                if (msg.getText().toString().trim() != "") {
                    if(!user.getString("getachievement").equals("0")){
                        chatblock.append("【" + achievement +"】" + user.getString("Name") + "：" + msg.getText().toString().trim() + "\n");
                        //聊天資料上傳資料庫---------------------------------

                        chatData.put("name", user.getString("Name"));
                        chatData.put("chatdata", "【" + user.getString("achievement") + "】" + user.getString("Name") + "：" + msg.getText().toString().trim());
                        chatData.saveInBackground();
                    } else{
                        chatblock.append(user.getString("Name") + "：" + msg.getText().toString().trim() + "\n");
                        //聊天資料上傳資料庫---------------------------------
                        chatData.put("name", user.getString("Name"));
                        chatData.put("chatdata", user.getString("Name") + "：" + msg.getText().toString().trim());
                        chatData.saveInBackground();
                    }
                    //傳送廣播給所有人------------------------------------
                    //Toast.makeText(this,user.getString("notifyid"),Toast.LENGTH_LONG).show();
                    push.setMessage(user.getString("notifyid") + "：" + user.getString("achievement")  + "：" + user.getString("Name") + "：" + msg.getText().toString().trim());
                    push.sendInBackground();


                }
               handler.post(mScrollToBottom);
                msg.setText("");
                break;
        }
    }

    //接收到廣播------------------------------------------
    public class Myreceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "Hello") {
                final TextView chatblock = (TextView) findViewById(R.id.textView3);
                final ParseObject chatData = new ParseObject("Chat");

                Bundle getchat = intent.getExtras();
                String getmsg = getchat.getString("msg");
                String getname = getchat.getString("name");
                String gettitle = getchat.getString("title");

                //Toast.makeText(context, getname, Toast.LENGTH_LONG).show();
                if (!user.getString("Name").equals(getname)) {
                    if(gettitle.equals("無")){
                        chatblock.append(getname + "：" + getmsg + "\n");
                    } else{
                        chatblock.append("【"+gettitle+"】"+getname + "：" + getmsg  + "\n");
                    }
                }
            }
            handler.post(mScrollToBottom);
        }
    }
    //Loading---------------------------------------------------------------------------------------------
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch(id) {
            case CUSTOM_PROGRESSDIALOG_ID:
                dialog = new Dialog(chat.this, R.style.progressDialog);
                dialog.setContentView(R.layout.progressdialog);
                dialog.setCancelable(false);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.alpha = 0.8f;
                dialog.getWindow().setAttributes(params);
                break;
        }
        return dialog;
    }

    //查詢聊天室資料庫-----------------------------------------------------
    public void getchatData() {
        final TextView chatblock = (TextView) findViewById(R.id.textView3);
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Chat");

        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> chatList, ParseException e) {
                for (ParseObject name : chatList) {
                    test = name.getString("chatdata");
                    chatblock.append(test + "\n");
                }
                dismissDialog(CUSTOM_PROGRESSDIALOG_ID);
                sendScroll();
            }
        });


    }
    //onDestroy-------------------------------------------------------
    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(chatreceiver);
    }
    //Runnable--------------------------------------------------------
    private Runnable mScrollToBottom = new Runnable() {
        @Override
        public void run() {
            final ScrollView chatscroll = (ScrollView) findViewById(R.id.scrollView);
            chatscroll.fullScroll(View.FOCUS_DOWN);
            dismissDialog(CUSTOM_PROGRESSDIALOG_ID);
        }
    };

//選單設定-----------------------------------------------------------------------------------------
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);

        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();


        MenuObject close = new MenuObject();
        close.setResource(R.drawable.newdel            );
        close.setBgColor(0x1C1C1C);

        MenuObject send = new MenuObject("聊天室");
        send.setResource(R.drawable.newchat);
        send.setBgColor(0x1C1C1C);

        MenuObject worker = new MenuObject("深蹲局");
        worker.setResource(R.drawable.newman
        );
        worker.setBgColor(0x1C1C1C);

        MenuObject eye = new MenuObject("天眼");
        eye.setResource(R.drawable.newdect);
        eye.setBgColor(0x1C1C1C);

        MenuObject map = new MenuObject("地圖");
        map.setResource(R.drawable.map);
        map.setBgColor(0x1C1C1C);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(worker);
        menuObjects.add(eye);
        menuObjects.add(map);
        return menuObjects;
    }


    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        Typeface face = Typeface.createFromAsset(getAssets(), "yyy.TTF");
        mToolBarTextView.setTypeface(face);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.goback);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(chat.this, home.class);
                startActivity(intent);
                chat.this.finish();
            }
        });
        mToolBarTextView.setText("聊天室");
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClick(final View clickedView, int position) {
        final LayoutInflater inflater = this.getLayoutInflater();
        final  View view = inflater.inflate(R.layout.edittext, null);
        AlertDialog.Builder waring = new AlertDialog.Builder(this);
        final SweetAlertDialog waring2 = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        switch (position){
            case 0:
                break;
            case 1:     //聊天室

                Toast.makeText(this, "已在聊天室", Toast.LENGTH_SHORT).show();
                break;
            case 2:     //進入深蹲局
                waring.setTitle("通關密語");
                waring.setView(view);
                waring.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText go = (EditText) view.findViewById(R.id.editText);
                        if (go.getText().toString().equals("csiehaha")) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(go.getWindowToken(), 0);
                            intent.setClass(chat.this, worker.class);
                            startActivity(intent);
                            chat.this.finish();
                        } else{
                            Toast.makeText(getBaseContext(), "想偷進來？門都沒有！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                waring.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                waring.show();
                break;
            case 3:     //天眼
                ParseUser user = ParseUser.getCurrentUser();
                waring2 .setTitleText("天眼自我防護系統")
                        .setContentText("天眼尚未解鎖，無法觀看")
                        .setConfirmText("了解")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                waring2.dismiss();
                            }
                        });
                if(user.getString("godeye").equals("1")){
                    intent.setClass(chat.this, godeye.class);
                    startActivity(intent);
                    chat.this.finish();

                }else{
                    waring2.show();
                }

                break;
            case 4 :        //地圖
                intent.setClass(chat.this, map.class);
                startActivity(intent);
                finish();
                break;
        }
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



