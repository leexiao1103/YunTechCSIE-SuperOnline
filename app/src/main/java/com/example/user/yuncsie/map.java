package com.example.user.yuncsie;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
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
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.yuncsie.ViewPager.godeye;
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
 * Created by user on 2015/11/26.
 */
public class map extends AppCompatActivity implements OnMenuItemClickListener, LocationListener {
    static final int CUSTOM_PROGRESSDIALOG_ID = 0;
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;
    Intent intent = new Intent();
    private static final String MAP_URL = "file:///android_asset/googlemap.html";
    private WebView googlemap;
    private boolean webviewReady = false;
    private Location mostRecentLocation = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();
        setupWebView();//設定webview
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    //onCreate結束-----------------------------------------------------------------------------------------
//地圖初始化------------------------------------------------------------------------------------------
    private void setupWebView() {
        googlemap = (WebView) findViewById(R.id.webview);
        googlemap.getSettings().setJavaScriptEnabled(true);//啟用Webview的JavaScript功能
        //Wait for the page to load then send the location information
        showDialog(CUSTOM_PROGRESSDIALOG_ID);
        googlemap.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                removeDialog(CUSTOM_PROGRESSDIALOG_ID);
                webviewReady = true;
            }
        });

        googlemap.loadUrl(MAP_URL);  //載入URL
      // googlemap.addJavascriptInterface(new JavaScriptInterface(), "android");
       if (mostRecentLocation != null) {
            //將畫面移至定位點的位置
            final String centerURL = "javascript:centerAt('" +
                    mostRecentLocation.getLatitude() + "," +
                    mostRecentLocation.getLongitude() + "')";
            final String centermark = "javascript:mark('" +
                    mostRecentLocation.getLatitude() + "','" +
                    mostRecentLocation.getLongitude() +  "')";

            if (webviewReady) googlemap.loadUrl(centerURL);
            if (webviewReady) googlemap.loadUrl(centermark);
        }
    }

    class JavaScriptInterface {
        @JavascriptInterface
        public double getLatitude() {
            return mostRecentLocation.getLatitude();
        }

        @JavascriptInterface
        public double getLongitude() {
            return mostRecentLocation.getLongitude();
        }
    }

    //Loading-------------------------------------------------------------------------------------------------
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case CUSTOM_PROGRESSDIALOG_ID:
                dialog = new Dialog(map.this, R.style.progressDialog);
                dialog.setContentView(R.layout.progressdialog);
                dialog.setCancelable(false);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.alpha = 0.8f;
                dialog.getWindow().setAttributes(params);
                break;
        }
        return dialog;
    }

    //location監聽---------------------------------------------------------------------------------------

    @Override
    public void onLocationChanged(Location location) {
        if (location !=null){
            //將畫面移至定位點的位置，呼叫在googlemaps.html中的centerAt函式
            final String centerURL = "javascript:centerAt(" +
                    location.getLatitude() + "," +
                    location.getLongitude()+ ")";
            if (webviewReady) googlemap.loadUrl(centerURL);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
                intent.setClass(map.this, home.class);
                startActivity(intent);
                map.this.finish();
            }
        });
        mToolBarTextView.setText("地圖");
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

                intent.setClass(map.this, chat.class);
                startActivity(intent);
                map.this.finish();
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
                            intent.setClass(map.this, worker.class);
                            startActivity(intent);
                            map.this.finish();
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
                    intent.setClass(map.this, godeye.class);
                    startActivity(intent);
                    map.this.finish();

                }else{
                    waring2.show();
                }

                break;
            case 4 :        //地圖
                Toast.makeText(this, "已在地圖", Toast.LENGTH_SHORT).show();
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




