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
public class siencemission extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getsience = new ParseObject("sience");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("sience");
    private String[] winename = new String[]{"jay", "chark", "power", "universepower", "getjay"};
    private String[] teamobjetID = new String[]{"XfHk03Iod6", "i3H8meFQ5E", "MUgevd1Y1O", "qelJqn72dn", "ivhhmsWWCk", "Uu71ruklGv", "r3WI8SrAhl", "JR09nBu71A","3MqNiFPGid", "9Lg2FrVlmX",
            "0fxMVWrYdj", "6gJ4Jv5FCo", "D4uXiuZ0na", "GeJpD86q81", "lkC7iNK5bx", "hHCKqdycAR", "BcTf2vwuG0", "RlL2U9DHBZ", "4tSUXG7ShM", "TzgAfPDKqi", "JTkpC7uhTX", "Jv6t8hyxZo", "VH6m4r71R7", "TO58i3k94j",
            "CixZNpyyJI", "SbDcOsVoq0", "jGQtTwFZFS", "TnyBupmrBL", "HPuJO7SwBO", "k7DTI12wUd", "0HYNSghin2", "FV6ctJX4XT", "xlfmoJA4xt"};
    private View view;
    List<String> siencelist;
    ListView listview;
    private HashMap<Integer,Boolean> ischecked;
    private AlarmManager am;
    private PendingIntent pi;
    private Calendar calendar;
    static String Tag = "siencelist";

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
        siencelist = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        siencelist.add("破滅o傑");siencelist.add("蒐集查克拉");siencelist.add("蒐集超能力");siencelist.add("蒐集宇宙能量");siencelist.add("獲得破滅");
        for(int i = 0; i < siencelist.size(); i++){
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
                    if (ischecked.get(0) == true) {
                        winelist.put(winename[0], true);
                    } else {
                        winelist.put(winename[0], false);
                    }
                    if (ischecked.get(1) == true && user.getBoolean("movie12check") == false) {
                        winelist.put(winename[1], true);
                        user.put("movie12check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(1) == false){
                        winelist.put(winename[1], false);
                    }
                    if (ischecked.get(2) == true) {
                        winelist.put(winename[2], true);
                    } else {
                        winelist.put(winename[2], false);
                    }
                    if (ischecked.get(3) == true) {
                        winelist.put(winename[3], true);
                    } else {
                        winelist.put(winename[3], false);
                    }
                    if (ischecked.get(4) == true && user.getBoolean("movie13check") == false) {
                        winelist.put(winename[4], true);
                        user.put("movie13check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(4) == false){
                        winelist.put(winename[4], false);
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
        final listadapter adapterItem0 = new listadapter(getActivity(),siencelist,ischecked);
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
                    if (winelist.getBoolean(winename[0])) {
                        ischecked.put(0, true);
                    } else {
                        ischecked.put(0, false);
                    }
                    if (winelist.getBoolean(winename[1])) {
                        ischecked.put(1, true);
                    } else {
                        ischecked.put(1, false);
                    }
                    if (winelist.getBoolean(winename[2])) {
                        ischecked.put(2, true);

                    } else {
                        ischecked.put(2, false);

                    }
                    if (winelist.getBoolean(winename[3])) {
                        ischecked.put(3, true);

                    } else {
                        ischecked.put(3, false);

                    }
                    if (winelist.getBoolean(winename[4])) {
                        ischecked.put(4, true);

                    } else {
                        ischecked.put(4, false);

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
