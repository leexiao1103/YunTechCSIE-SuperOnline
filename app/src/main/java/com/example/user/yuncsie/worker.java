package com.example.user.yuncsie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.yuncsie.ViewPager.godeye;
import com.example.user.yuncsie.itemlist.NPClist;
import com.example.user.yuncsie.itemlist.drivemission;
import com.example.user.yuncsie.itemlist.girlmission;
import com.example.user.yuncsie.itemlist.itemlist;
import com.example.user.yuncsie.itemlist.itemlist1;
import com.example.user.yuncsie.itemlist.killmission;
import com.example.user.yuncsie.itemlist.missionmoney;
import com.example.user.yuncsie.itemlist.siencemission;
import com.example.user.yuncsie.itemlist.suitmission;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
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
 * Created by user on 2015/11/4.
 */
public class worker extends AppCompatActivity implements  OnMenuItemClickListener {
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker);


        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        inititemlist();


    }
    //onCreate結束--------------------------------------------------------------------
    //初始化item清單------------------------------------------------------------------
    public void inititemlist(){
        final ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.mission_money, missionmoney.class)
                .add(R.string.kill, killmission.class)
                .add(R.string.drive, drivemission.class)
                .add(R.string.suit, suitmission.class)
                .add(R.string.sience, siencemission.class)
                .add(R.string.girl, girlmission.class)
                .add(R.string.wine, itemlist.class)
                .add(R.string.yang, itemlist1.class)
                .add(R.string.NPC, NPClist.class)
                .create());
        pager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(pager);

    }


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
                intent.setClass(worker.this, home.class);
                startActivity(intent);
                worker.this.finish();
            }
        });
        mToolBarTextView.setText("深蹲局");
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
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(go.getWindowToken(), 0);
                            intent.setClass(worker.this, worker.class);
                            startActivity(intent);
                            worker.this.finish();
                        } else {
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
                    intent.setClass(worker.this, godeye.class);
                    startActivity(intent);
                    worker.this.finish();

                }else{
                    waring2.show();
                }

                break;
            case 4 :        //地圖
                intent.setClass(worker.this, map.class);
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
