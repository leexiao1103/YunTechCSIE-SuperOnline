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
 * Created by user on 2015/12/9.
 */
public class itemlist extends Fragment {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getwine = new ParseObject("Wine");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("Wine");
    private String[] winename = new String[]{"lesswine", "tallwine", "wisky", "voga", "babyball", "paperball", "badball", "waterball", "godball", "card","card2", "card3", "card4"};
    private String[] teamobjetID = new String[]{"KWmhcXJ7Qf", "CP0qu0fVy2", "ApIEJAW2ui", "YKWSLApefP", "BiqobOS3WW", "kPeZ3IlWrQ", "CJMFuxqGFC", "Ui9SyMqiqN","ycNeV5UuK0", "05u65sfrfs",
    "rlCQTfjAoZ", "BUeXnx738w", "IzibCdhAWz", "cPLgflYbiv", "MDD7k4aYhl", "d3GmFGEMlV", "cU5jAWpMU4", "7BortKM5BU", "49PGFP7KYE", "xez7FQkRF0", "bG7I8FwlIS", "yW3b7mCmiq", "2aRQUsDrgt", "DMGfNuzhyE",
    "Q9uDmj1i0r", "8kLQh4p0Mn", "IIluH7e9xF", "edPdSud6Hf", "o8WysL8TIW", "IpqUNJvR3F", "W1Wcfkebow", "2wvJSP6JtI", "U52t5gQA7H", "hwnWk62TAo", "KOjbJETH1M"};
    private View view;
    List<String> list0;
    ListView listview0;
    private HashMap<Integer,Boolean> ischecked;

    static String Tag = "list0";

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
                list0.add("紹興酒");list0.add("高粱酒");list0.add("威士忌");list0.add("伏特加");
                list0.add("寶貝球");list0.add("紙球");list0.add("羽球");list0.add("水球");list0.add("天王球");list0.add("卡包1");list0.add("卡包2");list0.add("卡包3");list0.add("卡包4");
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

                    if (ischecked.get(0) && ischecked.get(1) && ischecked.get(2) && ischecked.get(3) && ischecked.get(4) && ischecked.get(5) && ischecked.get(6) && ischecked.get(7) && ischecked.get(8) && ischecked.get(9) && ischecked.get(10) && ischecked.get(11) && ischecked.get(12)) {
                        user.put("achi1", true);
                        // Toast.makeText(getActivity(), "獲得有錢人", Toast.LENGTH_SHORT).show();
                    } else {
                        user.put("achi1", false);
                    }
                    if (ischecked.get(0) && ischecked.get(1) && ischecked.get(2) && ischecked.get(3) ) {
                        user.put("achi4", true);
//                        Toast.makeText(getActivity(), "獲得酒鬼", Toast.LENGTH_SHORT).show();
                    } else {
                        user.put("achi4", false);
                    }
                    if (ischecked.get(4) && ischecked.get(5) && ischecked.get(6) && ischecked.get(7) && ischecked.get(8)) {
                        user.put("achi5", true);
                        // Toast.makeText(getActivity(), "獲得球王", Toast.LENGTH_SHORT).show();
                    } else {
                        user.put("achi5", false);
                    }

                user.saveInBackground();


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
                        //Toast.makeText(getActivity(), "0settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[0], false);
                    }
                    if (ischecked.get(1) == true) {
                        winelist.put(winename[1], true);
                        //Toast.makeText(getActivity(), "1settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[1], false);
                    }
                    if (ischecked.get(2) == true) {
                        winelist.put(winename[2], true);
                        //Toast.makeText(getActivity(), "2setfalse", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[2], false);
                    }
                    if (ischecked.get(3) == true) {
                        winelist.put(winename[3], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[3], false);
                    }
                    if (ischecked.get(4) == true) {
                        winelist.put(winename[4], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[4], false);
                    }
                    if (ischecked.get(5) == true) {
                        winelist.put(winename[5], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[5], false);
                    }

                    if (ischecked.get(6) == true) {
                        winelist.put(winename[6], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[6], false);
                    }
                    if (ischecked.get(7) == true) {
                        winelist.put(winename[7], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[7], false);
                    }
                    if (ischecked.get(8) == true) {
                        winelist.put(winename[8], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[8], false);
                    }
                    if (ischecked.get(9) == true) {
                        winelist.put(winename[9], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[9], false);
                    }
                    if (ischecked.get(10) == true) {
                        winelist.put(winename[10], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[10], false);
                    }
                    if (ischecked.get(11) == true) {
                        winelist.put(winename[11], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[11], false);
                    }
                    if (ischecked.get(12) == true) {
                        winelist.put(winename[12], true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        winelist.put(winename[12], false);
                    }

                }
                winelist.saveInBackground();
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
                    if (winelist.getBoolean(winename[5])) {
                        ischecked.put(5, true);

                    } else {
                        ischecked.put(5, false);

                    }
                    if (winelist.getBoolean(winename[6])) {
                        ischecked.put(6, true);

                    } else {
                        ischecked.put(6, false);

                    }
                    if (winelist.getBoolean(winename[7])) {
                        ischecked.put(7, true);

                    } else {
                        ischecked.put(7, false);

                    }
                    if (winelist.getBoolean(winename[8])) {
                        ischecked.put(8, true);

                    } else {
                        ischecked.put(8, false);

                    }
                    if (winelist.getBoolean(winename[9])) {
                        ischecked.put(9, true);

                    } else {
                        ischecked.put(9, false);

                    }
                    if (winelist.getBoolean(winename[10])) {
                        ischecked.put(10, true);

                    } else {
                        ischecked.put(10, false);
                    }
                    if (winelist.getBoolean(winename[11])) {
                        ischecked.put(11, true);
                    } else {
                        ischecked.put(11, false);
                    }
                    if (winelist.getBoolean(winename[12])) {
                        ischecked.put(12, true);
                    } else {
                        ischecked.put(12, false);
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
