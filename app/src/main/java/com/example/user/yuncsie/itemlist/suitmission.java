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
public class suitmission extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getwine = new ParseObject("suit");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("suit");
    private String[] suitname = new String[]{"mymonster", "pokamo", "waterturtle", "fishking", "strong", "picturebox", "yourmonster"};
    private String[] teamobjetID = new String[]{"LvypGAuJW9", "AVkhw8Lv3W", "DO36xOvCrM", "8BUYYjnuJC", "NGY5KSoLXT", "xZyY7SRYmb", "Xz9f99p0ek", "t1LFtuEq5q","iFoWQmyK4s", "vfpD5Wh2CO",
            "mPdOOaHnQs", "mLLS13Bjyy", "2mqc9hOmk9", "4G7qnULUAM", "nv8pnY6rXQ", "b6TXgINXPU", "AZxaKPryls", "QAaxzSgKy1", "Y0yewYcW75", "dnW5puUi2M", "C1NENWyIdC", "GR0MkZRXNm", "jJiVNdXOLj", "VOd1jtbdjr",
            "bCRQMjFbaB", "R7CYIkgwG1", "obCMJVRHl1", "hhSLQIb5Jv", "A4XxoCGN82", "5yEO6BYqGs", "LAsOaI064d", "NyEuCVyTO7", "5QsIiiUR2W", "n0baEPfZ4S", "br7OVRJicX"};
    private View view;
    List<String> suitlist;
    ListView listview;
    private HashMap<Integer,Boolean> ischecked;

    static String Tag = "suitlist";
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
        suitlist = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        suitlist.add("我的百變怪");suitlist.add("我要成為神奇寶貝大師");suitlist.add("收服水箭龜");suitlist.add("收服鯉魚王");suitlist.add("收服腕力");suitlist.add("完成圖鑑");suitlist.add("你的百變怪");
        for(int i = 0; i < suitlist.size(); i++){
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
                    if (ischecked.get(0) == true && user.getBoolean("movie11check") == false) {
                        winelist.put(suitname[0], true);
                        user.put("movie11check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(0) == false){
                        winelist.put(suitname[0], false);
                    }
                    if (ischecked.get(1) == true) {
                        winelist.put(suitname[1], true);
                    } else {
                        winelist.put(suitname[1], false);
                    }
                    if (ischecked.get(2) == true && user.getBoolean("movie15check") == false) {
                        winelist.put(suitname[2], true);
                        user.put("movie15check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(2) == false){
                        winelist.put(suitname[2], false);
                    }
                    if (ischecked.get(3) == true) {
                        winelist.put(suitname[3], true);
                    } else {
                        winelist.put(suitname[3], false);
                    }
                    if (ischecked.get(4) == true && user.getBoolean("movie10check") == false) {
                        winelist.put(suitname[4], true);
                        user.put("movie10check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(4) == false){
                        winelist.put(suitname[4], false);
                    }
                    if (ischecked.get(5) == true) {
                        winelist.put(suitname[5], true);
                    } else {
                        winelist.put(suitname[5], false);
                    }

                    if (ischecked.get(6) == true && user.getBoolean("movie9check") == false) {
                        winelist.put(suitname[6], true);
                        user.put("movie9check", true);
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                    } else if (ischecked.get(6) == false){
                        winelist.put(suitname[6], false);
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
        final listadapter adapterItem0 = new listadapter(getActivity(),suitlist,ischecked);
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
                    if (winelist.getBoolean(suitname[0])) {
                        ischecked.put(0, true);
                    } else {
                        ischecked.put(0, false);
                    }
                    if (winelist.getBoolean(suitname[1])) {
                        ischecked.put(1, true);
                    } else {
                        ischecked.put(1, false);
                    }
                    if (winelist.getBoolean(suitname[2])) {
                        ischecked.put(2, true);

                    } else {
                        ischecked.put(2, false);

                    }
                    if (winelist.getBoolean(suitname[3])) {
                        ischecked.put(3, true);

                    } else {
                        ischecked.put(3, false);

                    }
                    if (winelist.getBoolean(suitname[4])) {
                        ischecked.put(4, true);

                    } else {
                        ischecked.put(4, false);

                    }
                    if (winelist.getBoolean(suitname[5])) {
                        ischecked.put(5, true);

                    } else {
                        ischecked.put(5, false);

                    }
                    if (winelist.getBoolean(suitname[6])) {
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
