package com.example.user.yuncsie.itemlist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.user.yuncsie.R;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/12/19.
 */
public class drivemission extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getdrive = new ParseObject("drive");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("drive");
    private String[] drivename = new String[]{"helpyellow", "smallmakiyo", "smallnowine", "smallnofire", "smallnoelec", "gethus"};
    private String[] teamobjetID = new String[]{"nPRHvBpFSb", "E1U8PqfhPC", "ZnU26ON4oL", "VHnTzRNMe8", "8iWJU1aomq", "2ZwSiOqlKl", "o7B0bD2Ivy", "Mlq7WehXyn","MKunWUunLd", "v0XrykfnoQ",
            "ORAFHWHzzJ", "LflmejXWwx", "PrrXJWUQ6G", "1J94gmKDKh", "NYyayMoOST", "lYgzpSeJs7", "JsxxJIjV5Z", "voeGYiOxGs", "lGnY723hWA", "GmEiF0K2AE", "tGSn1gDw5W", "ZVDKXGMHkw", "GpcXszA1V4", "58SkQKUaX5",
            "majpw99zu6", "giv5xW29QC", "NT1izhjqCq", "enNmoXaWHS", "7Ykl6ays3I", "2ub3Vxsqfk", "OZvpUGOCXT", "jEWx3oWA3k", "0s1ugMwem1", "Uu17g1YXKp", "2i5hN7ez5s"};

    private View view;
    List<String> drivelist;
    ListView listview;
    private HashMap<Integer,Boolean> ischecked;
    private AlarmManager am;
    private PendingIntent pi;
    private Calendar calendar;
    static String Tag = "killlist";

    int pagenum;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagenum = FragmentPagerItem.getPosition(getArguments());
        Log.i(Tag, "onCreate()............");

        //設定定時廣播----------------------------------------------------------------------------
        Intent intent = new Intent("");
        intent.setAction("alarm");
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 95);
        am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        pi = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(Tag, "oncreateView()............");
        view = inflater.inflate(R.layout.itemlist, null);
        drivelist = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        drivelist.add("幫助黃伯");drivelist.add("雖小的馬ki有志龍花");drivelist.add("雖小之沒酒");drivelist.add("雖小之沒火");drivelist.add("雖小之沒電");drivelist.add("討回公道");
        for(int i = 0; i < drivelist.size(); i++){
            ischecked.put(i,false);
        }
        return view;
    }
    //----------------------------------------------------------------------------------------------------
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(Tag, "onActivityCreate()............");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(Tag, "onDestroyView()............");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "onDestroy()............");

        query.getInBackground(teamobjetID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject winelist, ParseException e) {
                if (e == null) {
                    if (ischecked.get(0) == true && user.getBoolean("movie7check") == false) {
                        winelist.put(drivename[0], true);
                        user.put("movie7check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(0) == false){
                        winelist.put(drivename[0], false);
                    }
                    if (ischecked.get(1) == true) {
                        winelist.put(drivename[1], true);
                    } else {
                        winelist.put(drivename[1], false);
                    }
                    if (ischecked.get(2) == true) {
                        winelist.put(drivename[2], true);
                    } else {
                        winelist.put(drivename[2], false);
                    }
                    if (ischecked.get(3) == true && user.getBoolean("movie8check") == false) {
                        winelist.put(drivename[3], true);
                        user.put("movie8check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(3) == false){
                        winelist.put(drivename[3], false);
                    }
                    if (ischecked.get(4) == true) {
                        winelist.put(drivename[4], true);

                    } else {
                        winelist.put(drivename[4], false);
                    }
                    if (ischecked.get(5) == true && user.getBoolean("movie6check") == false) {
                        winelist.put(drivename[5], true);
                        user.put("movie6check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(5) == false){
                        winelist.put(drivename[5], false);
                    }
                    winelist.saveInBackground();
                }

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(Tag, "onDetach()............");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(Tag, "onPause()............");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(Tag, "onResume()............");
        //安洋adapter-----------------------------------------------------------------------------
        listview = (ListView) view.findViewById(R.id.listView);
        final listadapter adapterItem0 = new listadapter(getActivity(),drivelist,ischecked);
        listview.setAdapter(adapterItem0);
        //點擊事件--------------------------------------------------------------------------------

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final CheckedTextView chkItem = (CheckedTextView) view.findViewById(R.id.check1);
                chkItem.toggle();

                ischecked.put(position, chkItem.isChecked());
            }
        });
        query.getInBackground(teamobjetID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject winelist, ParseException e) {
                if (e == null) {
                    if (winelist.getBoolean(drivename[0])) {
                        ischecked.put(0, true);
                    } else {
                        ischecked.put(0, false);
                    }
                    if (winelist.getBoolean(drivename[1])) {
                        ischecked.put(1, true);
                    } else {
                        ischecked.put(1, false);
                    }
                    if (winelist.getBoolean(drivename[2])) {
                        ischecked.put(2, true);

                    } else {
                        ischecked.put(2, false);

                    }
                    if (winelist.getBoolean(drivename[3])) {
                        ischecked.put(3, true);

                    } else {
                        ischecked.put(3, false);

                    }
                    if (winelist.getBoolean(drivename[4])) {
                        ischecked.put(4, true);

                    } else {
                        ischecked.put(4, false);

                    }
                    if (winelist.getBoolean(drivename[5])) {
                        ischecked.put(5, true);

                    } else {
                        ischecked.put(5, false);
                    }

                    listview.setAdapter(adapterItem0);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(Tag, "onStart()............");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(Tag, "onStop()............");
    }

}
