package com.example.user.yuncsie.itemlist;

import android.os.Bundle;
import android.os.Handler;
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
import java.util.TimerTask;


/**
 * Created by user on 2015/12/10.
 */
public class itemlist1 extends Fragment {

    private String[] drugsname = new String[]{"thanksdurgs", "sleepdrugs", "twodrugs", "getbigbig"};
    private String[] teamobjetID = new String[]{"QbrcTxZmMH", "58tnWOmVEc", "mTXousl0ju", "0geV8U6Kl4", "sSSruMT6Xn", "CXKaFG42sE", "XLowD3Jdnq", "aVf5qQWY9x","bZOVSgwygS", "0tfdmn5k1Y",
            "gNMEeph2Kh", "D9vu0ITRDA", "Me0idbW1bY", "UkyYd1wU2Z", "legxDqwKZS", "8T3oHddcga", "Pp0QAY0YvB", "I60N7CBcFV", "ZaSxr7z8Nr", "rOfYyIV40w", "0RvpPsV28o", "DZ8E7l787Y", "B2GtTewgAo", "zNzYoZX7Eb",
            "VMnQoAgRsx", "9NFPs42uRf", "vioD1NFcfS", "nOEyrpCxNM", "DFg3B8OPs5", "5r4oO5X6Sm", "fKu1j0SzaJ", "Q4xNSdPL1Q", "qjdgE1OnaZ", "jXubC8w9gO", "vlln4b3F7E"};
    private ParseObject getdrugs = new ParseObject("Drugs");
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("Drugs");
    private View view;
    ArrayList<String>  list1;
    ListView  listview1;
    private HashMap<Integer,Boolean> ischecked;
    private Handler handler = new Handler();
       // 這個用來記錄哪幾個 item 是被打勾的
    static String Tag = "list1";

    int pagenum;

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
            if (ischecked.get(0) && ischecked.get(1) && ischecked.get(2) && ischecked.get(3)) {
                user.put("achi2", true);
            } else {
                user.put("achi2", false);
            }

            user.saveInBackground();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagenum = FragmentPagerItem.getPosition(getArguments());
        ischecked = new HashMap<>();
        list1 = new ArrayList<>();
        //安洋院長初始化
        list1.add("咳嗽藥");
        list1.add("征露丸");
        list1.add("安眠藥");
        list1.add("威而鋼");
        for (int c = 0; c < list1.size(); c++){
            ischecked.put(c,false);
        }
        /*Toast.makeText(getActivity(),"create" + ischecked.get(0).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),"create" +ischecked.get(1).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),"create" +ischecked.get(2).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),"create" +ischecked.get(3).toString(),Toast.LENGTH_SHORT).show();*/
        Log.i(Tag, "onCreate().........................");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.itemlist1, null);
        Log.i(Tag, "onCreateView().........................");


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

        handler.post(refresh);
                   /* if (ischecked.get(0) && ischecked.get(1) && ischecked.get(2) && ischecked.get(3)) {
                        user.put("achi2", true);
                    } else {
                        user.put("achi2", false);
                    }

                user.saveInBackground();*/


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "onDestroy()............");

        query.getInBackground(teamobjetID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject drugslist, ParseException e) {
                if (e == null) {
                    if (ischecked.get(0) == true) {
                        drugslist.put(drugsname[0], "1");
                        //Toast.makeText(getActivity(), "0settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        drugslist.put(drugsname[0], "0");
                    }
                    if (ischecked.get(1) == true) {
                        drugslist.put(drugsname[1], "1");
                        //Toast.makeText(getActivity(), "1settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        drugslist.put(drugsname[1], "0");
                    }
                    if (ischecked.get(2) == true) {
                        drugslist.put(drugsname[2], "1");
                        //Toast.makeText(getActivity(), "2setfalse", Toast.LENGTH_SHORT).show();
                    } else {
                        drugslist.put(drugsname[2], "0");
                    }
                    if (ischecked.get(3) == true) {
                        drugslist.put(drugsname[3], "1");
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        drugslist.put(drugsname[3], "0");
                    }

                }
                drugslist.saveInBackground();
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
        //安洋adapter-----------------------------------------------------------------------------
        listview1 = (ListView) view.findViewById(R.id.listView2);
        final list1adapter adapterItem1 = new list1adapter(getActivity(),list1,ischecked);
        listview1.setAdapter(adapterItem1);
        //點擊事件--------------------------------------------------------------------------------

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final CheckedTextView chkItem = (CheckedTextView) view.findViewById(R.id.check1);
                chkItem.toggle();

                ischecked.put(position,chkItem.isChecked());

            }
        });
        query.getInBackground(teamobjetID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject drugslist, ParseException e) {
                if (e == null) {
                    if (drugslist.getString("thanksdurgs").equals("1")) {
                        ischecked.put(0, true);
                        //Toast.makeText(getActivity(), "0settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        ischecked.put(0, false);
                        //Toast.makeText(getActivity(), "0setfalse", Toast.LENGTH_SHORT).show();
                    }
                    if (drugslist.getString("sleepdrugs").equals("1")) {
                        ischecked.put(1, true);
                        //Toast.makeText(getActivity(), "1settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        ischecked.put(1, false);
                       // Toast.makeText(getActivity(), "1setfalse", Toast.LENGTH_SHORT).show();
                    }
                    if (drugslist.getString("twodrugs").equals("1")) {
                        ischecked.put(2, true);
                        //Toast.makeText(getActivity(), "2setfalse", Toast.LENGTH_SHORT).show();
                    } else {
                        ischecked.put(2, false);
                        //Toast.makeText(getActivity(), "2setfalse", Toast.LENGTH_SHORT).show();
                    }
                    if (drugslist.getString("getbigbig").equals("1")) {
                        ischecked.put(3, true);
                        //Toast.makeText(getActivity(), "3settrue", Toast.LENGTH_SHORT).show();
                    } else {
                        ischecked.put(3, false);
                        //Toast.makeText(getActivity(), "3setfalse", Toast.LENGTH_SHORT).show();
                    }
                    listview1.setAdapter(adapterItem1);
                }
            }
        });
        Log.i(Tag, "onResume()............");
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
