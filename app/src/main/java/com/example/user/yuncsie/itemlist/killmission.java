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
public class killmission extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getkill = new ParseObject("kill");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("kill");
    private String[] winename = new String[]{"becomeshield", "cube", "hitmakiyo", "getcube"};

    private String[] teamobjetID = new String[]{"N7x5XzaeRF", "fqUXIEYxDa", "LwYZf6hHsK", "lKtZ3fFadg", "gcT2NcOibk", "eunEWa5d6k", "XZCO0FQWPb", "a5h8nhkauw","UFOvWFBosA", "zpozplxLPQ",
            "05xcpj0qZV", "Xci3EwjcIU", "TwobxaA8Fi", "8YWkLydqTH", "G0Z4jGN2AC", "4ATzHo0UNX", "3Bx3E1egM7", "X28qdAsElO", "HIV2c5l9xg", "5odqKazFbt", "UNoZfcrjLz", "zNo1kJmeiG", "h8r01Y3eS1", "GVvxXIWWPG",
            "0futstyDKy", "TF1xSv5b9c", "2RlRdnJUoM", "2DG9Evf7Gb", "VeRdrJERDS", "m31yQGYz21", "npH2J27uES", "sjaShKjrKi", "cBdqO5S36i", "AAvLR6YkOu", "VJm56OeOWw" };
    private View view;
    private int rannum;
    List<String> killlist;
    ListView listview;
    private HashMap<Integer,Boolean> ischecked;

    static String Tag = "killlist";
    private AlarmManager am;
    private PendingIntent pi;
    private Calendar calendar;
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
        killlist = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        killlist.add("成為神盾局的一員");killlist.add("對付馬ki腰子攏毀");killlist.add("取得宇宙魔方");killlist.add("奪回魔方");
        for(int i = 0; i < killlist.size(); i++){
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
                    if (ischecked.get(0) == true && user.getBoolean("moviecheck") == false ) {
                        winelist.put(winename[0], true);
                        user.put("moviecheck", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

                        Log.i("check", "get");
                    } else if (ischecked.get(0) == false) {
                        winelist.put(winename[0], false);
                    }
                    if (ischecked.get(1) == true) {
                        winelist.put(winename[1], true);
                    } else {
                        winelist.put(winename[1], false);
                    }
                    if (ischecked.get(2) == true) {
                        winelist.put(winename[2], true);

                    } else {
                        winelist.put(winename[2], false);
                    }
                    if (ischecked.get(3) == true && user.getBoolean("movie2check") == false) {
                        winelist.put(winename[3], true);
                        user.put("movie2check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(3) == false){
                        winelist.put(winename[3], false);
                    }
                    winelist.saveInBackground();
                    user.saveInBackground();
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
        final listadapter adapterItem0 = new listadapter(getActivity(),killlist,ischecked);
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
