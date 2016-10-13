package com.example.user.yuncsie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.user.yuncsie.ViewPager.godeye;
import com.parse.ParseObject;
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
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by user on 2015/11/3.
 */
public class home extends AppCompatActivity implements View.OnClickListener, OnMenuItemClickListener {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseQuery<ParseObject> querydrugs = ParseQuery.getQuery("Drugs");
    private List<String> achievement;
    private Spinner spinner;
    private ArrayAdapter<String> achivmentList;
    private Context mContext;
    private SweetAlertDialog waring;
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    private Handler mThreadHandler;
    private HandlerThread mThread;
    private SoundPool sp;
    private CircleIndicator circleindicator;



    private Intent intent = new Intent();
    static String Tag = "home";

    private ViewPager mViewPager;
    private List<View> viewList;
    private View homeview;
    private View skill;
    private View titlepage;
    private View homepage;
    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private View v5;
    private View v6;
    private View v7;
    private View v8;
    private View v9;
    private View v10;
    private int music;
    private final Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Tag, "onCreate--------------------");
        setContentView(R.layout.homepager);

        sp= new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music = sp.load(this, R.raw.gon, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        final TextView title = (TextView) findViewById(R.id.text_view_toolbar_title);
        Typeface face = Typeface.createFromAsset(getAssets(),"yyy.TTF");
        title.setTypeface(face);
        title.setText("特務憑證");
        //pager設定----------------------------------------------------------------------------------------------------
        mViewPager = (ViewPager) findViewById(R.id.viewpager5);
        circleindicator = (CircleIndicator) findViewById(R.id.indicator);

       /* Timer backgroundtimer = new Timer();
        backgroundtimer.schedule(backgroundtask, 0, 3000);*/

        final LayoutInflater mInflater = getLayoutInflater().from(this);
        Toast.makeText(home.this,Integer.toString(user.getInt("ID")), Toast.LENGTH_SHORT);
        v1 = mInflater.inflate(R.layout.home, null);
        v2 = mInflater.inflate(R.layout.skillpage, null);
        v3 = mInflater.inflate(R.layout.wearecsie, null);
        v4 = mInflater.inflate(R.layout.csietaxi, null);
        v5 = mInflater.inflate(R.layout.sienceroom, null);
        v6 = mInflater.inflate(R.layout.gress, null);
        v7 = mInflater.inflate(R.layout.bar, null);
        v8 = mInflater.inflate(R.layout.yang, null);
        v9 = mInflater.inflate(R.layout.agaency, null);
        v10 = mInflater.inflate(R.layout.shield, null);

        titlepage = mInflater.inflate(R.layout.toolbar, null);
        homepage = mInflater.inflate(R.layout.homepager, null);
        // View v3 = mInflater.inflate(R.layout.test3, null);

        viewList = new ArrayList<View>();
        viewList.add(v1);
        viewList.add(v2);
        viewList.add(v9);
        viewList.add(v4);
        viewList.add(v10);
        viewList.add(v7);
        viewList.add(v5);
        viewList.add(v6);
        viewList.add(v8);
        viewList.add(v3);


        homeview = viewList.get(0);
        skill = viewList.get(2);
        mViewPager.setAdapter(new homepageradapter(viewList));
        mViewPager.setCurrentItem(0);
        mViewPager.setPageTransformer(true, new RotateUpTransformer());
        circleindicator.setViewPager(mViewPager);
        //--------------------------------------------------------------------------------------------

        final ParseUser user = ParseUser.getCurrentUser();
        /*user.put("used","1");
        user.saveInBackground();*/
        //警告提示設定----------------------------------------------------------------------------------------------
        waring = new SweetAlertDialog(this);
        waring.setTitleText("確定使用技能？")
                .setContentText("將有30秒緩衝，請確定要觸發的NPC在附近")
                .setCancelText("取消")
                .setConfirmText("確定")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        user.saveInBackground();
                    }
                });

        ImageView skillimage = (ImageView) homeview.findViewById(R.id.imageView2);
        skillimage.setOnClickListener(home.this);

        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        handler.post(refresh2);
       // inithome();
        Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {

            @Override
            public void run() {
                initachi();
            }}, 8000);


        //執行續設定---------------------------------------------------------------------------------------
       // handler.post(refresh);
    }
 //onCreate結束------------------------------------------------------------------------------------------------
 //onClick--------------------------------------------------------------------------------------------------------
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.imageView2:               //點擊技能
                final int skilltime = user.getInt("skill");
                final int temp = 0;
                //警告提示設定----------------------------------------------------------------
                waring = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                waring.setTitleText("確定使用技能？")
                        .setContentText("將有30秒緩衝，請確定觸發NPC在附近")
                        .setCancelText("取消")
                        .setConfirmText("確定")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                newskilltime(skilltime);
                                waring.dismiss();
                                intent.setClass(home.this, skilllayout.class);
                                startActivity(intent);
                            }
                        });
                if(skilltime != 0){
                    waring.show();
                }
                break;

        }
    }
//設定skill葉面--------------------------------------------------------------------------------------------------------
    public void initskilllayout(){
        Button goback = (Button) findViewById(R.id.button13);
        ImageView useskill = (ImageView) findViewById(R.id.imageView3);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("goback", "click");
            }
        });
        switch (user.getString("agancy")){
            case "東廠":
                useskill.setImageResource(R.drawable.chinese);
                break;
            case "OO7":
                useskill.setImageResource(R.drawable.my_friend);
                break;
            case "小":
                useskill.setImageResource(R.drawable.getmonster);
                break;
            case "IMF":
                useskill.setImageResource(R.drawable.gameboy);
                break;
            case "世界政府":
                useskill.setImageResource(R.drawable.hoandgon);
                break;
        }
    }
//刷新技能使用----------------------------------------------------------------------------------------------------------
    public void newskilltime(int x){
        final TextView skillthetime = (TextView) findViewById(R.id.textView11);
        final ImageView skillimage = (ImageView) findViewById(R.id.imageView2);
        x--;
        if(x == 0){
            skillimage.setClickable(false);
            switch (user.getString("agancy")){
                case "東廠":
                    skillimage.setImageResource(R.drawable.chinese2);
                    break;
                case "OO7":
                    skillimage.setImageResource(R.drawable.my_friend2);
                    break;
                case "小":
                    skillimage.setImageResource(R.drawable.getmonster2);
                    break;
                case "IMF":
                    skillimage.setImageResource(R.drawable.gameboy2);
                    break;
                case "世界政府":
                    skillimage.setImageResource(R.drawable.hoandgon2);
                    break;
            }
        }
        user.put("skill", Integer.valueOf(x));
        user.saveInBackground();
        skillthetime.setText("還可使用" + Integer.toString(x) + "次");
    }
//初始化home介面------------------------------------------------------------------------------------------------------
    public void inithome() {
        Typeface face = Typeface.createFromAsset(getAssets(),"yyy.TTF");
        TextView text2 = (TextView) v2.findViewById(R.id.textView2);
        TextView text15 = (TextView) v2.findViewById(R.id.textView15);
        TextView text16 = (TextView) v2.findViewById(R.id.textView16);
        TextView text17 = (TextView) v2.findViewById(R.id.textView17);
        TextView text18 = (TextView) v2.findViewById(R.id.textView18);
        TextView text19 = (TextView) v2.findViewById(R.id.textView19);
        TextView text20 = (TextView) v2.findViewById(R.id.textView20);
        TextView text21 = (TextView) v2.findViewById(R.id.textView21);
        TextView text22 = (TextView) v2.findViewById(R.id.textView22);
        TextView text23 = (TextView) v2.findViewById(R.id.textView23);
        TextView text24 = (TextView) v2.findViewById(R.id.textView24);
        TextView text25 = (TextView) v2.findViewById(R.id.textView25);
        TextView text26 = (TextView) v2.findViewById(R.id.textView26);
        TextView text27 = (TextView) v2.findViewById(R.id.textView27);
        TextView text28 = (TextView) v2.findViewById(R.id.textView28);
        TextView text29 = (TextView) v3.findViewById(R.id.textView29);
        TextView text30 = (TextView) v3.findViewById(R.id.textView30);
        TextView text31 = (TextView) v3.findViewById(R.id.textView31);
        TextView text32 = (TextView) v4.findViewById(R.id.textView32);
        TextView text33 = (TextView) v4.findViewById(R.id.textView33);
        TextView text34 = (TextView) v4.findViewById(R.id.textView34);
        TextView text35 = (TextView) v5.findViewById(R.id.textView35);
        TextView text36 = (TextView) v5.findViewById(R.id.textView36);
        TextView text37 = (TextView) v5.findViewById(R.id.textView37);
        TextView text38 = (TextView) v6.findViewById(R.id.textView38);
        TextView text39 = (TextView) v6.findViewById(R.id.textView39);
        TextView text40 = (TextView) v6.findViewById(R.id.textView40);
        TextView text41 = (TextView) v6.findViewById(R.id.textView41);
        TextView text42 = (TextView) v6.findViewById(R.id.textView42);
        TextView text43 = (TextView) v6.findViewById(R.id.textView43);
        TextView text44 = (TextView) v7.findViewById(R.id.textView44);
        TextView text45 = (TextView) v7.findViewById(R.id.textView45);
        TextView text46 = (TextView) v7.findViewById(R.id.textView46);
        TextView text47 = (TextView) v8.findViewById(R.id.textView47);
        TextView text48 = (TextView) v8.findViewById(R.id.textView48);
        TextView text49 = (TextView) v8.findViewById(R.id.textView49);
        TextView text50 = (TextView) v9.findViewById(R.id.textView2);
        TextView text51 = (TextView) v9.findViewById(R.id.textView15);
        TextView text52 = (TextView) v9.findViewById(R.id.textView16);
        TextView text53 = (TextView) v9.findViewById(R.id.textView17);
        TextView text54 = (TextView) v9.findViewById(R.id.textView18);
        TextView text55 = (TextView) v9.findViewById(R.id.textView19);
        TextView text56 = (TextView) v9.findViewById(R.id.textView20);
        TextView text57 = (TextView) v9.findViewById(R.id.textView21);
        TextView text58 = (TextView) v9.findViewById(R.id.textView22);
        TextView text59 = (TextView) v9.findViewById(R.id.textView23);
        TextView text60 = (TextView) v9.findViewById(R.id.textView24);
        TextView text61 = (TextView) v9.findViewById(R.id.textView25);
        TextView text62 = (TextView) v9.findViewById(R.id.textView26);
        TextView text63 = (TextView) v9.findViewById(R.id.textView27);
        TextView text64 = (TextView) v9.findViewById(R.id.textView28);
        TextView text65 = (TextView) v10.findViewById(R.id.textView65);
        TextView text66 = (TextView) v10.findViewById(R.id.textView66);
        TextView text67 = (TextView) v10.findViewById(R.id.textView67);





        TextView tricktext = (TextView) homeview.findViewById(R.id.textView14);
        TextView skilltime = (TextView) homeview.findViewById(R.id.textView11);
        TextView skillname = (TextView) homeview.findViewById(R.id.textView10);
        TextView teamname = (TextView) homeview.findViewById(R.id.textView5);
        TextView agency = (TextView) homeview.findViewById(R.id.textView6);
        TextView money = (TextView) homeview.findViewById(R.id.textView7);
        TextView chooseachivment = (TextView) homeview.findViewById(R.id.textView9);
        ImageView skillimage = (ImageView) homeview.findViewById(R.id.imageView2);
        ImageView killimage = (ImageView) homeview.findViewById(R.id.imageView4);
        ImageView driveimage = (ImageView) homeview.findViewById(R.id.imageView5);
        ImageView suitimage = (ImageView) homeview.findViewById(R.id.imageView6);
        ImageView getgirlimage = (ImageView) homeview.findViewById(R.id.imageView7);
        ImageView sienceimage = (ImageView) homeview.findViewById(R.id.imageView8);
        chooseachivment.setTypeface(face);
        teamname.setTypeface(face);
        agency.setTypeface(face);
        money.setTypeface(face);
        money.setTypeface(face);
        skillname.setTypeface(face);
        skilltime.setTypeface(face);
        tricktext.setTypeface(face);
        text2.setTypeface(face);text15.setTypeface(face);text16.setTypeface(face);text17.setTypeface(face);text18.setTypeface(face);text19.setTypeface(face);text20.setTypeface(face);text21.setTypeface(face);
        text22.setTypeface(face);text23.setTypeface(face);text24.setTypeface(face);text25.setTypeface(face);text26.setTypeface(face);text27.setTypeface(face);text28.setTypeface(face);
        text29.setTypeface(face);text30.setTypeface(face);text31.setTypeface(face);text32.setTypeface(face);text33.setTypeface(face);text34.setTypeface(face);text35.setTypeface(face);
        text36.setTypeface(face);text37.setTypeface(face);text38.setTypeface(face);text39.setTypeface(face);text40.setTypeface(face);text41.setTypeface(face);text42.setTypeface(face);
        text43.setTypeface(face);text44.setTypeface(face);text45.setTypeface(face);text46.setTypeface(face);text47.setTypeface(face);text48.setTypeface(face);text49.setTypeface(face);
        text50.setTypeface(face);text51.setTypeface(face);text52.setTypeface(face);text53.setTypeface(face);text54.setTypeface(face);text55.setTypeface(face);text56.setTypeface(face);
        text57.setTypeface(face);text58.setTypeface(face);text59.setTypeface(face);text60.setTypeface(face);text61.setTypeface(face);text62.setTypeface(face);text63.setTypeface(face);text64.setTypeface(face);
        text65.setTypeface(face);text66.setTypeface(face);text67.setTypeface(face);

        //初始化技術---------------------------------------------------------------------------------
        if(killimage.getDrawable() == null || driveimage.getDrawable() ==  null || suitimage.getDrawable() == null || getgirlimage.getDrawable() == null || sienceimage.getDrawable() == null){
            if(user.getBoolean("trick1")) {
                if (killimage.getDrawable() == null) {
                    killimage.setImageResource(R.drawable.kill);
                }
                else if(driveimage.getDrawable() == null){
                    driveimage.setImageResource(R.drawable.kill);
                }
                else if(suitimage.getDrawable() == null){
                    suitimage.setImageResource(R.drawable.kill);
                }
                else if(getgirlimage.getDrawable() == null){
                    getgirlimage.setImageResource(R.drawable.kill);
                }
                else if(sienceimage.getDrawable() == null){
                    sienceimage.setImageResource(R.drawable.kill);
                }

            }
            if(user.getBoolean("trick2")){
                if (killimage.getDrawable() == null) {
                    killimage.setImageResource(R.drawable.drive);
                }
                else if(driveimage.getDrawable() == null){
                    driveimage.setImageResource(R.drawable.drive);
                }
                else  if(suitimage.getDrawable() == null){
                    suitimage.setImageResource(R.drawable.drive);
                }
                else  if(getgirlimage.getDrawable() == null){
                    getgirlimage.setImageResource(R.drawable.drive);
                }
                else  if(sienceimage.getDrawable() == null){
                    sienceimage.setImageResource(R.drawable.drive);
                }
            }
            if(user.getBoolean("trick3")){
                if (killimage.getDrawable() == null) {
                    killimage.setImageResource(R.drawable.suit);
                }
                else if(driveimage.getDrawable() == null){
                    driveimage.setImageResource(R.drawable.suit);
                }
                else if(suitimage.getDrawable() == null){
                    suitimage.setImageResource(R.drawable.suit);
                }
                else if(getgirlimage.getDrawable() == null){
                    getgirlimage.setImageResource(R.drawable.suit);
                }
                else if(sienceimage.getDrawable() == null){
                    sienceimage.setImageResource(R.drawable.suit);
                }
            }
            if(user.getBoolean("trick4")){
                if (killimage.getDrawable() == null) {
                    killimage.setImageResource(R.drawable.getgirl);
                }
                else if(driveimage.getDrawable() == null){
                    driveimage.setImageResource(R.drawable.getgirl);
                }
                else if(suitimage.getDrawable() == null){
                    suitimage.setImageResource(R.drawable.getgirl);
                }
                else if(getgirlimage.getDrawable() == null){
                    getgirlimage.setImageResource(R.drawable.getgirl);
                }
                else if(sienceimage.getDrawable() == null){
                    sienceimage.setImageResource(R.drawable.getgirl);
                }
            }
            if(user.getBoolean("trick5")){
                if (killimage.getDrawable() == null) {
                    killimage.setImageResource(R.drawable.sience);
                }
                else  if(driveimage.getDrawable() == null){
                    driveimage.setImageResource(R.drawable.sience);
                }
                else if(suitimage.getDrawable() == null){
                    suitimage.setImageResource(R.drawable.sience);
                }
                else if(getgirlimage.getDrawable() == null){
                    getgirlimage.setImageResource(R.drawable.sience);
                }
                else  if(sienceimage.getDrawable() == null){
                    sienceimage.setImageResource(R.drawable.sience);
                }
            }
        }


        //初始化隊伍&技能內容----------------------------------------------------------------------------
        teamname.setText(user.getString("Name"));
        agency.setText("隸屬組織：" + user.getString("agancy") );
        money.setText("金錢：" + user.getString("money"));
        switch (user.getString("agancy")){
            case "東廠":
                skillname.setText("專屬技能：強國人");
                skilltime.setText("還可使用" + user.getInt("skill") + "次");
                if( user.getInt("skill") == 0){
                    skillimage.setImageResource(R.drawable.chinese2);
                    skillimage.setClickable(false);
                }else{
                    skillimage.setImageResource(R.drawable.chinese);
                }
                break;
            case "OO7":
                skillname.setText("專屬技能：藥劑精通");
                skilltime.setText("還可使用" + user.getInt("skill") + "次");
                if( user.getInt("skill") == 0){
                    skillimage.setImageResource(R.drawable.my_friend2);
                    skillimage.setClickable(false);
                }else{
                    skillimage.setImageResource(R.drawable.my_friend);
                }
                break;
            case "小":
                skillname.setText("專屬技能：通靈之術");
                skilltime.setText("還可使用" + user.getInt("skill") + "次");
                if( user.getInt("skill") == 0){
                    skillimage.setImageResource(R.drawable.getmonster2);
                    skillimage.setClickable(false);
                }else{
                    skillimage.setImageResource(R.drawable.getmonster);
                }

                break;
            case "IMF":
                skillname.setText("專屬技能：遊戲Boy");
                skilltime.setText("還可使用" + user.getInt("skill") + "次");
                if( user.getInt("skill") == 0){
                    skillimage.setImageResource(R.drawable.gameboy2);
                    skillimage.setClickable(false);
                }else{
                    skillimage.setImageResource(R.drawable.gameboy);
                }
                break;
            case "世界政府":
                skillname.setText("專屬技能：指槍");
                skilltime.setText("還可使用" + user.getInt("skill") + "次");
                if( user.getInt("skill") == 0){
                    skillimage.setImageResource(R.drawable.hoandgon2);
                    skillimage.setClickable(false);
                }else{
                    skillimage.setImageResource(R.drawable.hoandgon);
                }
                break;
        }
    }

//成就選單增加--------------------------------------------------------------------------------------------------
    public void achiadd(){
        Log.i("achi","++");
            if(user.getBoolean("achi1") ){
                achievement.add("有錢人");
                Toast.makeText(home.this, "獲得有錢人稱號", Toast.LENGTH_SHORT).show();
                Log.i("money", "++");
            }
            if(user.getBoolean("achi2") ) {
                achievement.add("藥頭");
                Toast.makeText(home.this, "獲得藥頭稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi3") ){
                achievement.add("神奇寶貝大師");
                Toast.makeText(home.this, "獲得神奇寶貝大師稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi4") ){
                achievement.add("酒鬼");
                Toast.makeText(home.this, "獲得酒鬼稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi5") ){
                achievement.add("球王");
                Toast.makeText(home.this, "獲得球王稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi6") ){
                achievement.add("使命必達");
                Toast.makeText(home.this, "獲得使命必達稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi7") ){
                achievement.add("書嘎Turn口");
                Toast.makeText(home.this, "獲得書嘎Turn口稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi8")){
                achievement.add("作弊狗");
                Toast.makeText(home.this, "獲得作弊狗稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi9") ){
                achievement.add("我爸高進");
                Toast.makeText(home.this, "獲得我爸高進稱號", Toast.LENGTH_SHORT).show();
            }
            if(user.getBoolean("achi10") ){
                achievement.add("拍立得");
                Toast.makeText(home.this, "獲得拍立得稱號", Toast.LENGTH_SHORT).show();
            }
    }
 //初始化成就內容----------------------------------------------------------------------------
    public void initachi(){
        mContext = this.getApplicationContext();
        spinner = (Spinner) homeview.findViewById(R.id.spinner);
        achievement = new ArrayList<>();
        if(user.getBoolean("achi11")){
            achievement.add("無");
            achievement.add(user.getString("achievement"));
            achivmentList = new ArrayAdapter<String>(home.this, android.R.layout.simple_spinner_item, achievement);
            spinner.setAdapter(achivmentList);
            spinner.setSelection(1, true);
        }else{
            achievement.add("無");
            achiadd();
            achivmentList = new ArrayAdapter<String>(home.this, android.R.layout.simple_spinner_item, achievement);
            spinner.setAdapter(achivmentList);
            spinner.setSelection(Integer.valueOf(user.getString("getachievement")), true);
        }
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(mContext, "你選的是" + achievement.get(position), Toast.LENGTH_SHORT).show();
                user.put("achievement",  achievement.get(position).toString());
                user.put("getachievement", Integer.toString(position));
                user.saveInBackground();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
//Task--------------------------------------------------------------------------
private TimerTask backgroundtask = new TimerTask() {
    @Override
    public void run() {
        handler.post(refresh);
    }
};
    private Runnable refresh = new Runnable() {
        @Override
        public void run() {
            initachi();
        }
    };
    private Runnable refresh2 = new Runnable() {
        @Override
        public void run() {
            inithome();
        }
    };

//選單設定--------------------------------------------------------------
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
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setNavigationIcon(R.drawable.ak47_50);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(home.this, "怎麼樣?這把槍帥吧!", Toast.LENGTH_LONG).show();
                    sp.play(music, 1, 1, 0, 0, 1);
                }
            });

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
                    intent.setClass(home.this, chat.class);
                    startActivity(intent);
                    home.this.finish();
                    Toast.makeText(this, "進入聊天室", Toast.LENGTH_SHORT).show();
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
                                intent.setClass(home.this, worker.class);
                                startActivity(intent);
                                home.this.finish();
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
                        intent.setClass(home.this, godeye.class);
                        startActivity(intent);
                        home.this.finish();

                    }else{
                        waring2.show();
                    }

                    break;
                case 4 :        //地圖
                    intent.setClass(home.this, map.class);
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

