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
public class girlmission extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getgirl = new ParseObject("girl");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("girl");
    private String[] girlname = new String[]{"eighthead", "nurseboy", "muscle", "superman", "tokayo", "loving", "girlmaster"};
    private String[] teamobjetID = new String[]{"uh7U5PNC44", "GHjc3KGcJa", "PG1Y3dPJya", "sQJ06ze3bP", "1oNG62asCO", "Rw7aCkZLNe", "V4xaNgddjH", "8p0cD7sZbV","7bnjfBtTmz", "goq97UhZmm",
            "oUFb5S0q2C", "Pz1rbVD5Y4", "EuKCIuYC66", "q9V8zqwIgu", "xQVDUmt5hw", "aSVK7MDQPW", "2hMQF3XddT", "xKpGCybd6s", "9K2BbdQj7N", "fqjC7WKXtv", "Z3LFZYyBGU", "42BDhgxsAF", "KeURN6ppD3", "2eFGQTFjZp",
            "93BrvgCBgc", "VxD4pjtLfT", "OMUSy5GAy5", "QtuD5JLXss", "ixs5QLKlnq", "S0ItnwiV5q", "pUBdFRWYpM", "U9e8weoLqX", "jcetCRnqvW", "8Lzh5C7Pcf", "x8PezOUyXL"};
    private View view;
    List<String> girllist;
    ListView listview;
    private HashMap<Integer,Boolean> ischecked;
    private AlarmManager am;
    private PendingIntent pi;
    private Calendar calendar;
    static String Tag = "girllist";

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
        girllist = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        girllist.add("把不到妹妹就巴頭");girllist.add("小護士的心上人");girllist.add("肌肉猛男");girllist.add("緊身衣超人");girllist.add("東京甩尾");girllist.add("戀愛ING");girllist.add("把妹高手");
        for(int i = 0; i < girllist.size(); i++){
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
                    if (ischecked.get(0) == true && user.getBoolean("movie14check") == false) {
                        winelist.put(girlname[0], true);
                        user.put("movie14check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(0) == false) {
                        winelist.put(girlname[0], false);
                    }
                    if (ischecked.get(1) == true) {
                        winelist.put(girlname[1], true);
                    } else {
                        winelist.put(girlname[1], false);
                    }
                    if (ischecked.get(2) == true && user.getBoolean("movie5check") == false) {
                        winelist.put(girlname[2], true);
                        user.put("movie5check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(2) == false) {
                        winelist.put(girlname[2], false);
                    }
                    if (ischecked.get(3) == true) {
                        winelist.put(girlname[3], true);
                    } else {
                        winelist.put(girlname[3], false);
                    }
                    if (ischecked.get(4) == true && user.getBoolean("movie4check") == false) {
                        winelist.put(girlname[4], true);
                        user.put("movie4check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(4) == false) {
                        winelist.put(girlname[4], false);
                    }
                    if (ischecked.get(5) == true) {
                        winelist.put(girlname[5], true);
                    } else {
                        winelist.put(girlname[5], false);
                    }

                    if (ischecked.get(6) == true && user.getBoolean("movie3check") == false) {
                        winelist.put(girlname[6], true);
                        user.put("movie3check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(6) == false) {
                        winelist.put(girlname[6], false);
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
        final listadapter adapterItem0 = new listadapter(getActivity(),girllist,ischecked);
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
                    if (winelist.getBoolean(girlname[0])) {
                        ischecked.put(0, true);
                    } else {
                        ischecked.put(0, false);
                    }
                    if (winelist.getBoolean(girlname[1])) {
                        ischecked.put(1, true);
                    } else {
                        ischecked.put(1, false);
                    }
                    if (winelist.getBoolean(girlname[2])) {
                        ischecked.put(2, true);

                    } else {
                        ischecked.put(2, false);

                    }
                    if (winelist.getBoolean(girlname[3])) {
                        ischecked.put(3, true);

                    } else {
                        ischecked.put(3, false);

                    }
                    if (winelist.getBoolean(girlname[4])) {
                        ischecked.put(4, true);

                    } else {
                        ischecked.put(4, false);

                    }
                    if (winelist.getBoolean(girlname[5])) {
                        ischecked.put(5, true);

                    } else {
                        ischecked.put(5, false);

                    }
                    if (winelist.getBoolean(girlname[6])) {
                        ischecked.put(6, true);

                    } else {
                        ischecked.put(6, false);
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
