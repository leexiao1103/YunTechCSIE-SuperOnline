package com.example.user.yuncsie;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2015/12/25.
 */
public class end extends AppCompatActivity {
    TextView totalteamname;
    TextView totalgodeye;
    TextView totalmission;
    TextView totalmoney;
    TextView totalachi;
    TextView totalend;
    TextView total;
    TextView test;
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("final");
    private final Handler handler = new Handler();
    private int endpoint;
    private int mission = 0;
    private int achi = 0;
    private int finalmoney = 0;
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseQuery<ParseObject> querykill = ParseQuery.getQuery("kill");
    private String[] killname = new String[]{"becomeshield", "cube", "hitmakiyo", "getcube"};
    private String[] killid = new String[]{"N7x5XzaeRF", "fqUXIEYxDa", "LwYZf6hHsK", "lKtZ3fFadg", "gcT2NcOibk", "eunEWa5d6k", "XZCO0FQWPb", "a5h8nhkauw","UFOvWFBosA", "zpozplxLPQ",
            "05xcpj0qZV", "Xci3EwjcIU", "TwobxaA8Fi", "8YWkLydqTH", "G0Z4jGN2AC", "4ATzHo0UNX", "3Bx3E1egM7", "X28qdAsElO", "HIV2c5l9xg", "5odqKazFbt", "UNoZfcrjLz", "zNo1kJmeiG", "h8r01Y3eS1", "GVvxXIWWPG",
            "0futstyDKy", "TF1xSv5b9c", "2RlRdnJUoM", "2DG9Evf7Gb", "VeRdrJERDS", "m31yQGYz21", "npH2J27uES", "sjaShKjrKi", "cBdqO5S36i", "AAvLR6YkOu", "VJm56OeOWw"};
    private ParseQuery<ParseObject> querysience = ParseQuery.getQuery("sience");
    private String[] siencename = new String[]{"jay", "chark", "power", "universepower", "getjay"};
    private String[] sienceid = new String[]{"XfHk03Iod6", "i3H8meFQ5E", "MUgevd1Y1O", "qelJqn72dn", "ivhhmsWWCk", "Uu71ruklGv", "r3WI8SrAhl", "JR09nBu71A","3MqNiFPGid", "9Lg2FrVlmX",
            "0fxMVWrYdj", "6gJ4Jv5FCo", "D4uXiuZ0na", "GeJpD86q81", "lkC7iNK5bx", "hHCKqdycAR", "BcTf2vwuG0", "RlL2U9DHBZ", "4tSUXG7ShM", "TzgAfPDKqi", "JTkpC7uhTX", "Jv6t8hyxZo", "VH6m4r71R7", "TO58i3k94j",
            "CixZNpyyJI", "SbDcOsVoq0", "jGQtTwFZFS", "TnyBupmrBL", "HPuJO7SwBO", "k7DTI12wUd", "0HYNSghin2", "FV6ctJX4XT", "xlfmoJA4xt"};
    private ParseQuery<ParseObject> querysuit = ParseQuery.getQuery("suit");
    private String[] suitname = new String[]{"mymonster", "pokamo", "waterturtle", "fishking", "strong", "picturebox", "yourmonster"};
    private String[] suitID = new String[]{"LvypGAuJW9", "AVkhw8Lv3W", "DO36xOvCrM", "8BUYYjnuJC", "NGY5KSoLXT", "xZyY7SRYmb", "Xz9f99p0ek", "t1LFtuEq5q","iFoWQmyK4s", "vfpD5Wh2CO",
            "mPdOOaHnQs", "mLLS13Bjyy", "2mqc9hOmk9", "4G7qnULUAM", "nv8pnY6rXQ", "b6TXgINXPU", "AZxaKPryls", "QAaxzSgKy1", "Y0yewYcW75", "dnW5puUi2M", "C1NENWyIdC", "GR0MkZRXNm", "jJiVNdXOLj", "VOd1jtbdjr",
            "bCRQMjFbaB", "R7CYIkgwG1", "obCMJVRHl1", "hhSLQIb5Jv", "A4XxoCGN82", "5yEO6BYqGs", "LAsOaI064d", "NyEuCVyTO7", "5QsIiiUR2W", "n0baEPfZ4S", "br7OVRJicX"};
    private ParseQuery<ParseObject> querygirl = ParseQuery.getQuery("girl");
    private String[] girlname = new String[]{"eighthead", "nurseboy", "muscle", "superman", "tokayo", "loving", "girlmaster"};
    private String[] girlID = new String[]{"uh7U5PNC44", "GHjc3KGcJa", "PG1Y3dPJya", "sQJ06ze3bP", "1oNG62asCO", "Rw7aCkZLNe", "V4xaNgddjH", "8p0cD7sZbV","7bnjfBtTmz", "goq97UhZmm",
            "oUFb5S0q2C", "Pz1rbVD5Y4", "EuKCIuYC66", "q9V8zqwIgu", "xQVDUmt5hw", "aSVK7MDQPW", "2hMQF3XddT", "xKpGCybd6s", "9K2BbdQj7N", "fqjC7WKXtv", "Z3LFZYyBGU", "42BDhgxsAF", "KeURN6ppD3", "2eFGQTFjZp",
            "93BrvgCBgc", "VxD4pjtLfT", "OMUSy5GAy5", "QtuD5JLXss", "ixs5QLKlnq", "S0ItnwiV5q", "pUBdFRWYpM", "U9e8weoLqX", "jcetCRnqvW", "8Lzh5C7Pcf", "x8PezOUyXL"};
    private ParseQuery<ParseObject> querydrive = ParseQuery.getQuery("drive");
    private String[] drivename = new String[]{"helpyellow", "smallmakiyo", "smallnowine", "smallnofire", "smallnoelec", "gethus"};
    private String[] driveID = new String[]{"nPRHvBpFSb", "E1U8PqfhPC", "ZnU26ON4oL", "VHnTzRNMe8", "8iWJU1aomq", "2ZwSiOqlKl", "o7B0bD2Ivy", "Mlq7WehXyn","MKunWUunLd", "v0XrykfnoQ",
            "ORAFHWHzzJ", "LflmejXWwx", "PrrXJWUQ6G", "1J94gmKDKh", "NYyayMoOST", "lYgzpSeJs7", "JsxxJIjV5Z", "voeGYiOxGs", "lGnY723hWA", "GmEiF0K2AE", "tGSn1gDw5W", "ZVDKXGMHkw", "GpcXszA1V4", "58SkQKUaX5",
            "majpw99zu6", "giv5xW29QC", "NT1izhjqCq", "enNmoXaWHS", "7Ykl6ays3I", "2ub3Vxsqfk", "OZvpUGOCXT", "jEWx3oWA3k", "0s1ugMwem1", "Uu17g1YXKp", "2i5hN7ez5s"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Typeface face = Typeface.createFromAsset(getAssets(),"yyy.TTF");

        totalteamname = (TextView) findViewById(R.id.textView51);
        totalmission = (TextView) findViewById(R.id.textView53);
        totalmoney = (TextView) findViewById(R.id.textView54);
        totalachi = (TextView) findViewById(R.id.textView55);
        totalend = (TextView) findViewById(R.id.textView54);
        total = (TextView) findViewById(R.id.textView57);
        test = (TextView) findViewById(R.id.textView58);
        totalteamname.setText(user.getString("teamName"));
        Timer backgroundtimer = new Timer();
        backgroundtimer.schedule(backgroundtask, 0, 500);

        endpoint = user.getInt("end");
        finalmoney = Integer.valueOf(user.getString("money"));
        achiadd();
        killadd();
        suitadd();
        driveadd();
        girladd();
        sienceadd();
        trickadd();

        if(user.getBoolean("gettotal") == true){
            totalend.setText("解出謎底");
        }else{
            totalend.setText("未解出謎底");
        }
        if(user.getString("godeye").equals("1")){
            mission++;
        }




    }
//onCreate結束--------------------------------------------------------------------------------------
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
            totalachi.setText("共完成" + Integer.toString(achi) + "項成就");
            totalmission.setText("共完成" + Integer.toString(mission) + "項主要任務");
            totalmoney.setText("金錢" + user.getString("money") + "元");
            total.setText(Integer.toString(20 + (mission * 18) + (achi*5) + (finalmoney / 1000) + endpoint));
            test.setText(Integer.toString(mission) + "/" + Integer.toString(achi) + "/" + Integer.toString(endpoint) + "/" + Integer.toString(finalmoney));
        }
    };
//成就++---------------------------------------------------------------------------------------------
    public void achiadd(){
        if(user.getBoolean("achi1")){
            achi++;
        }
        if(user.getBoolean("achi2")){
            achi++;
        }
        if(user.getBoolean("achi3")){
            achi++;
        }
        if(user.getBoolean("achi4")){
            achi++;
        }
        if(user.getBoolean("achi5")){
            achi++;
        }
        if(user.getBoolean("achi6")){
            achi++;
        }
        if(user.getBoolean("achi7")){
            achi++;
        }
        if(user.getBoolean("achi8")){
            achi++;
        }
        if(user.getBoolean("achi9")){
            achi++;
        }
        if(user.getBoolean("achi10")){
            achi++;
        }
    }
//暗殺++---------------------------------------------------------------------------------------------
    public void killadd(){
        querykill.getInBackground(killid[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject killlist, ParseException e) {
                if (e == null) {
                    for(int i = 0; i < 4; i++ ){
                        if(killlist.getBoolean(killname[i])){
                            mission++;
                            Log.i("kill","++");
                        }
                    }
                }
            }
        });
    }
//化學++------------------------------------------------------------------------------------------------
    public void sienceadd(){
        querysience.getInBackground(sienceid[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject siencelist, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < 5; i++) {
                        if (siencelist.getBoolean(siencename[i])) {
                            mission++;
                            Log.i("sience", "++");
                        }
                    }
                }
            }
        });

    }
//開車++-----------------------------------------------------------------------------------------------
    public void driveadd(){
        querydrive.getInBackground(driveID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject drivelist, ParseException e) {
                if (e == null) {
                    for(int i = 0; i <6; i++){
                        if(drivelist.getBoolean(drivename[i])){
                            mission++;
                            Log.i("drive","++");
                        }
                    }
                }
            }
        });

    }
//變裝++-----------------------------------------------------------------------------------------------
    public void suitadd(){
        querysuit.getInBackground(suitID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject suitlist, ParseException e) {
                if (e == null) {
                    for(int i = 0; i <7; i++){
                        if(suitlist.getBoolean(suitname[i])){
                            mission++;
                            Log.i("suit","++");
                        }
                    }
                }
            }
        });

    }
//搭訕++------------------------------------------------------------------------------------------------
    public void girladd(){
        querygirl.getInBackground(girlID[user.getInt("ID")], new GetCallback<ParseObject>() {
            public void done(ParseObject girllist, ParseException e) {
                if (e == null) {
                    for(int i = 0; i < 7; i++){
                        if(girllist.getBoolean(girlname[i])){
                            mission++;
                            Log.i("girl","++");
                        }
                    }
                }
            }
        });
    }
 //trick++---------------------------------------------------------------------------------------------
    public void trickadd(){
        if(user.getBoolean("trick1")){
            mission++;
        }
        if(user.getBoolean("trick2")){
            mission++;
        }
        if(user.getBoolean("trick3")){
            mission++;
        }
        if(user.getBoolean("trick4")){
            mission++;
        }
        if(user.getBoolean("trick5")){
            mission++;
        }

    }
}


