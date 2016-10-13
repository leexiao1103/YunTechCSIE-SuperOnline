package com.example.user.yuncsie.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.yuncsie.R;
import com.example.user.yuncsie.playmovie;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by user on 2015/11/24.
 */
public class movie extends Fragment {
    int pagenum;
    private ListView listview;
    private View view;
    private ArrayAdapter<String> listAdapter;
    private Intent intent;
    private Bundle bundle;

    ParseUser user = ParseUser.getCurrentUser();
    String[] movie = new String[]{"2015年4月1日-1", "2015年4月1日-2", "2015年4月1日-3", "2015年5月15日-1", "2015年5月15日-2", "2015年5月15日-3", "2015年9月28日-1",
            "2015年9月28日-2", "2015年9月28日-3", "2015年11月28日-1", "2015年11月28日-2", "2015年11月28日-3", "2015年10月10日-1", "2015年10月10日-2", "2015年10月10日-3"};
    String[] moviedata = new String[]{"movie1_1", "movie1_2", "movie1_3", "movie2_1", "movie2_2", "movie2_3", "movie3_1", "movie3_2", "movie3_3", "movie4_1",
            "movie4_2", "movie4_3", "movie5_1", "movie5_2", "movie5_3"};
    ArrayList<String> movielist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagenum = FragmentPagerItem.getPosition(getArguments());
        intent = new Intent();
        bundle = new Bundle();

        movielist = new ArrayList<>();
        initmovielist();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie, null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //安洋adapter-----------------------------------------------------------------------------
        listview = (ListView) view.findViewById(R.id.listView3);
        listAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, movielist);
        listview.setAdapter(listAdapter);
        //點擊事件--------------------------------------------------------------------------------

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                switch (movielist.get(position)){
                    case "2015年4月1日-1" :
                        intent.setClass(getActivity(), playmovie.class);
                        bundle.putInt("ags", 0);
                        intent.putExtras(bundle);
                        startActivity(intent);
                       // Toast.makeText(getActivity(), movielist.get(position), Toast.LENGTH_LONG).show();
                        break;
                    case "2015年4月1日-2" :
                        intent.setClass(getActivity(), playmovie.class);
                        bundle.putInt("ags", 1);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年4月1日-3" :
                        intent.setClass(getActivity(), playmovie.class);
                        bundle.putInt("ags", 2);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年5月15日-1" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 3);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年5月15日-2" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 4);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年5月15日-3" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 5);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年9月28日-1" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 6);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年9月28日-2" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 7);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年9月28日-3" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 8);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年11月28日-1" :
                        intent.setClass(getActivity(), playmovie.class);
                        bundle.putInt("ags", 12);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年11月28日-2" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 13);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年11月28日-3" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 14);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年10月10日-1" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 9);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年10月10日-2" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 10);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case "2015年10月10日-3" :
                        intent.setClass(getActivity(), playmovie.class);

                        bundle.putInt("ags", 11);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                }

            }
        });

    }
//初始化天眼內容-----------------------------------------------------------------------------------------
    public void initmovielist(){
        switch (pagenum){
            case 0 :
                for(int i = 0; i < 3; i++){
                    if(user.getBoolean(moviedata[i])){
                        movielist.add(movie[i]);
                    }
                }
                break;
            case 1 :
                for(int i = 3; i < 6; i++){
                    if(user.getBoolean(moviedata[i])){
                        movielist.add(movie[i]);
                    }
                }
                break;
            case 2 :
                for(int i = 6; i < 9; i++){
                    if(user.getBoolean(moviedata[i])){
                        movielist.add(movie[i]);
                    }
                }
                break;
            case 3 :
                for(int i = 9; i < 12; i++){
                    if(user.getBoolean(moviedata[i])){
                        movielist.add(movie[i]);
                    }
                }
                break;
            case 4 :
                for(int i = 12; i < 15; i++){
                    if(user.getBoolean(moviedata[i])){
                        movielist.add(movie[i]);
                    }
                }
                break;
            case 5 :

                break;
        }
    }


}
