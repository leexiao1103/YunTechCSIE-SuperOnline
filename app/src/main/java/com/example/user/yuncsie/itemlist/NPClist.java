package com.example.user.yuncsie.itemlist;

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
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/12/14.
 */
public class NPClist extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getNPC = new ParseObject("NPClist");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("NPClist");
    private String[] NPCname = new String[]{"tentime", "losefive", "winfive", "winten", "postfive", "getdream"};

    private String[] teamobjetID = new String[]{"3k7migykEV", "Lq2Y8sGoQv", "21PqTrv6rn", "n0A1zDRVV8", "FOUjvqWdyX", "mxbmXpkpO3", "qAeKsfuBHC", "AtvzpiMwKp","zyDausiKhg", "Avoe3l3ybn",
            "4WhTf8UpUo", "597ihqKwSu", "Av81JoX3bb", "gH0Cn9J68z", "GZHc8S5rRI", "ap4rwDLD5j", "vMaLYEnHdm", "IDwJGOsNAk", "kfR5HxW0bA", "xdK6M2H4UN", "yYHiCwC8yC", "32SwLFUcdt", "4NsjCIYn3s", "70IeJecRI5",
            "KqA2Wxq7SU", "UjQu46wJpy", "X8BmCa3fXU", "9MCt1vnKP8", "8zH6gYLKkF", "aS0r2p1qCZ", "uHanTc7bMI", "oM3U0sAa5B", "d0pJfKXN5c", "r1EZsCVKOB", "cwzm5HXKJO"};
    private View view;
    List<String> list0;
    ListView listview0;
    private HashMap<Integer,Boolean> ischecked;

    static String Tag = "NPClist";

    int pagenum;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagenum = FragmentPagerItem.getPosition(getArguments());
        Log.i(Tag, "onCreate()............");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(Tag, "oncreateView()............");
        view = inflater.inflate(R.layout.itemlist, null);
        list0 = new ArrayList<>();
        ischecked = new HashMap<>();
        //酒保
        list0.add("送貨10次");list0.add("連輸5次");list0.add("連贏5次");list0.add("連贏10次");list0.add("拍到5個NPC");list0.add("抓到超夢");
        for(int i = 0; i < list0.size(); i++){
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

        query.getInBackground(teamobjetID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject NPClist, ParseException e) {
                if (e == null) {
                    if (ischecked.get(0) == true) {
                        NPClist.put(NPCname[0], true);
                        user.put("achi6", true);
                    } else {
                        NPClist.put(NPCname[0], false);
                        user.put("achi6", false);
                    }
                    if (ischecked.get(1) == true) {
                        NPClist.put(NPCname[1], true);
                        user.put("achi7", true);
                    } else {
                        NPClist.put(NPCname[1], false);
                        user.put("achi7", false);
                    }
                    if (ischecked.get(2) == true) {
                        NPClist.put(NPCname[2], true);
                        user.put("achi9", true);
                    } else {
                        NPClist.put(NPCname[2], false);
                        user.put("achi9", false);
                    }
                    if (ischecked.get(3) == true) {
                        NPClist.put(NPCname[3], true);
                        user.put("achi8", true);
                    } else {
                        NPClist.put(NPCname[3], false);
                        user.put("achi8", false);
                    }
                    if (ischecked.get(4) == true) {
                        NPClist.put(NPCname[4], true);
                        user.put("achi10", true);
                    } else {
                        NPClist.put(NPCname[4], false);
                        user.put("achi10", false);
                    }
                    if (ischecked.get(5) == true) {
                        NPClist.put(NPCname[5], true);
                        user.put("achi3", true);
                    } else {
                        NPClist.put(NPCname[5], false);
                        user.put("achi3", false);
                    }
                }
                user.saveInBackground();
                NPClist.saveInBackground();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "onDestroy()............");



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
        listview0 = (ListView) view.findViewById(R.id.listView);
        final listadapter adapterItem0 = new listadapter(getActivity(),list0,ischecked);
        listview0.setAdapter(adapterItem0);
        //點擊事件--------------------------------------------------------------------------------

        listview0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    if (winelist.getBoolean(NPCname[0])) {
                        ischecked.put(0, true);
                    } else {
                        ischecked.put(0, false);
                    }
                    if (winelist.getBoolean(NPCname[1])) {
                        ischecked.put(1, true);
                    } else {
                        ischecked.put(1, false);
                    }
                    if (winelist.getBoolean(NPCname[2])) {
                        ischecked.put(2, true);

                    } else {
                        ischecked.put(2, false);

                    }
                    if (winelist.getBoolean(NPCname[3])) {
                        ischecked.put(3, true);

                    } else {
                        ischecked.put(3, false);

                    }
                    if (winelist.getBoolean(NPCname[4])) {
                        ischecked.put(4, true);

                    } else {
                        ischecked.put(4, false);
                    }
                    if (winelist.getBoolean(NPCname[5])) {
                        ischecked.put(5, true);
                    } else {
                        ischecked.put(5, false);
                    }
                    listview0.setAdapter(adapterItem0);
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