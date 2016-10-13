package com.example.user.yuncsie.itemlist;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.yuncsie.R;
import com.example.user.yuncsie.end;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class missionmoney extends Fragment implements View.OnClickListener {
    private ParseUser user = ParseUser.getCurrentUser();
    private ParseObject getend = new ParseObject("final");
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("final");
    private Handler handler = new Handler();
    private View view;
    private int end;
    static String Tag = "moneyfragment";
    String swt = "" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        query.getInBackground("iY2T9pDP9m", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    end = object.getInt("score");
                }
            }
        });

        Log.i(Tag, "onCreate()............");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(Tag, "onCreateView()............");
        view = inflater.inflate(R.layout.mission_money, null);
        final Button getgodeye = (Button) view.findViewById(R.id.button4);
        final Button plus_money = (Button) view.findViewById(R.id.button7);
        final Button dec_money = (Button) view.findViewById(R.id.button8);
        final Button done = (Button) view.findViewById(R.id.button9);
        final Button initdata = (Button) view.findViewById(R.id.button14);
        final Button killbtn = (Button) view.findViewById(R.id.button5);
        final Button drivebtn = (Button) view.findViewById(R.id.button6);
        final Button suitbtn = (Button) view.findViewById(R.id.button10);
        final Button girlbtn = (Button) view.findViewById(R.id.button11);
        final Button siencebtn = (Button) view.findViewById(R.id.button12);
        final EditText getmoney = (EditText) view.findViewById(R.id.editText4);
        final Button gettotal = (Button) view.findViewById(R.id.button15);
        final Button gofinal = (Button) view.findViewById(R.id.button16);
        getmoney.clearFocus();

        gofinal.setOnClickListener(this);
        gettotal.setOnClickListener(this);
        plus_money.setOnClickListener(this);
        dec_money.setOnClickListener(this);
        done.setOnClickListener(this);
        getgodeye.setOnClickListener(this);
        initdata.setOnClickListener(this);
        killbtn.setOnClickListener(this);
        drivebtn.setOnClickListener(this);
        suitbtn.setOnClickListener(this);
        girlbtn.setOnClickListener(this);
        siencebtn.setOnClickListener(this);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getmoney.getWindowToken(), 0);
        return view;
    }


    @Override
    public void onClick(View v) {
        int total = 0;
        final EditText getmoney = (EditText) view.findViewById(R.id.editText4);
        final RadioButton mission_plus = (RadioButton) view.findViewById(R.id.radioButton);
        final RadioButton mission_dec = (RadioButton) view.findViewById(R.id.radioButton2);
        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        final SweetAlertDialog pDialog2 = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        final SweetAlertDialog pDialog3 = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        final SweetAlertDialog waring = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        final SweetAlertDialog gettotal = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
        pDialog.setTitleText("防呆裝置")
                .setContentText("請手抄紀錄一份")
                .setCancelText("取消")
                .setConfirmText("確定上傳")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        user.saveInBackground();
                    //    Toast.makeText(getActivity(), "共完成" + user.getString("mission") + "項任務", Toast.LENGTH_SHORT).show();
                    }
                });
        pDialog2.setTitleText("防呆裝置")
                .setContentText("請手抄紀錄一份")
                .setCancelText("取消")
                .setConfirmText("確定上傳")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        swtskill(swt);
                        user.saveInBackground();
                    }
                });
        pDialog3.setTitleText("防呆裝置")
                .setContentText("請手抄紀錄一份")
                .setCancelText("取消")
                .setConfirmText("確定上傳")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                        user.put("end", Integer.valueOf(end));
                        user.saveInBackground();


                        end = end - 20;
                        query.getInBackground("iY2T9pDP9m", new GetCallback<ParseObject>() {
                            public void done(ParseObject object, ParseException e) {
                                if (e == null) {
                                    object.put("score", Integer.valueOf(end));
                                }
                                object.saveInBackground();
                            }
                        });
                        Toast.makeText(getActivity(), "共完成" + Integer.toString(end) , Toast.LENGTH_SHORT).show();

                    }
                });
        gettotal.setTitleText("防呆裝置")
                .setContentText("請手抄紀錄一份")
                .setCancelText("取消")
                .setConfirmText("確定上傳")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        Intent intent2 = new Intent();
                        intent2.setClass(getActivity(), end.class);
                        user.saveInBackground();
                        startActivity(intent2);
                        getActivity().finish();
                    }
                });
        waring.setTitleText("非常嚴重")
                .setContentText("這按下去後果不堪設想別亂按")
                .setCancelText("取消")
                .setConfirmText("確定上傳")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("成功！")
                                .setContentText("資料上傳完成")
                                .setConfirmText("確定")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        handler.post(refresh);
                        handler.post(refresh2);
                        handler.post(refresh3);
                    }
                });

        switch (v.getId()) {
            case R.id.button7:  //加錢
                if (getmoney.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "不要留空", Toast.LENGTH_SHORT).show();
                    break;
                }
                total = Integer.valueOf(user.getString("money")) + Integer.valueOf(getmoney.getText().toString());
                user.put("money", Integer.toString(total));
                user.saveInBackground();
                Toast.makeText(getActivity(), "加" + getmoney.getText(), Toast.LENGTH_SHORT).show();
                getmoney.setText("");
                Toast.makeText(getActivity(), "剩餘"+user.getString("money")+"元", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button8:  //扣錢
                if (getmoney.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "不要留空", Toast.LENGTH_SHORT).show();
                    break;
                }
                total = Integer.valueOf(user.getString("money")) - Integer.valueOf(getmoney.getText().toString());
                if (total < 0) {
                    Toast.makeText(getActivity(), "再扣就沒錢啦！", Toast.LENGTH_SHORT).show();
                }else if (getmoney.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "不要留空", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.put("money", Integer.toString(total));
                    user.saveInBackground();
                }
                Toast.makeText(getActivity(), "剩餘"+user.getString("money")+"元", Toast.LENGTH_SHORT).show();
                getmoney.setText("");
                break;
            case R.id.button9:  //任務確定
                if (mission_plus.isChecked()) {
                    total = Integer.valueOf(user.getString("mission")) + 1;
                    user.put("mission", Integer.toString(total));
                    pDialog.show();

                } else if (mission_dec.isChecked()) {
                    total = Integer.valueOf(user.getString("mission")) - 1;
                    user.put("mission", Integer.toString(total));
                    pDialog.show();

                }
                break;
            case R.id.button4:              //天眼

                swt = "天眼";
                pDialog2.show();
                break;
            case R.id.button5:              //暗殺
                swt = "暗殺";
                pDialog2.show();
                break;
            case R.id.button6:              //開車
                swt = "開車";
                pDialog2.show();
                break;
            case R.id.button10:             //便裝
                swt = "變裝";
                pDialog2.show();
                break;
            case R.id.button11:             //搭訕
                swt = "搭訕";
                pDialog2.show();
                break;
            case R.id.button12:             //化學
                swt = "化學";
                pDialog2.show();
                break;
            case R.id.button14:             //初始化
                waring.show();
                break;
            case R.id.button15:
                pDialog3.show();
                break;
            case R.id.button16:
                gettotal.show();
                break;
        }
    }
    private Runnable refresh = new Runnable() {
        @Override
        public void run() {

            switch (user.getString("agancy")){
                case "OO7":
                    user.put("skill", 1);
                    user.saveInBackground();
                    break;
                case "世界政府":
                    user.put("skill", 2);
                    user.saveInBackground();
                    break;
                case "IMF":
                    user.put("skill", 3);
                    user.saveInBackground();
                    break;
                case "小":
                    user.put("skill", 1);
                    user.saveInBackground();
                    break;
                case "東廠":
                    user.put("skill", 2);
                    user.saveInBackground();
                    break;
            }
        }
    };
    private Runnable refresh2 = new Runnable() {
        @Override
        public void run() {
            user.put("trick1", false);user.put("trick2", false);user.put("trick3", false);user.put("trick4", false);user.put("trick5", false);user.put("mission", "0");user.put("money", "0");user.put("godeye", "0");
            user.put("getachievement", "0");user.put("achievement", "無");user.put("achi1", false);user.put("achi2", false);user.put("achi3", false);user.put("achi4", false);user.put("achi5", false);user.put("achi6", false);user.put("achi7", false);user.put("achi8", false);user.put("achi9", false);user.put("achi10", false);
            user.put("movie1_1", false);user.put("movie1_2", false);user.put("movie1_3", false);user.put("movie3_1", false);user.put("movie3_2", false);user.put("movie3_3", false);user.put("movie4_1", false);user.put("movie2_1", false);user.put("movie2_2", false);user.put("movie2_3", false);
            user.saveInBackground();
            user.put("movie4_2", false);user.put("movie4_3", false);user.put("movie5_1", false);user.put("movie5_2", false);user.put("movie5_3", false);
            user.put("moviecheck", false);user.put("movie2check", false); user.put("movie3check", false);user.put("movie4check", false); user.put("movie5check", false);user.put("movie6check", false); user.put("movie7check", false);user.put("movie8check", false); user.put("movie9check", false);user.put("movie10check", false);
            user.put("movie11check", false);user.put("movie12check", false); user.put("movie13check", false);user.put("movie14check", false); user.put("movie15check", false);
            user.put("checkachi1", false);user.put("checkachi2", false);user.put("checkachi3", false);user.put("checkachi4", false);user.put("checkachi5", false);user.put("checkachi6", false);user.put("checkachi7", false);user.put("checkachi8", false);user.put("checkachi9", false);user.put("checkachi10", false);


        }
    };
    private Runnable refresh3 = new Runnable() {
        @Override
        public void run() {

            user.put("movie4_2", false);user.put("movie4_3", false);user.put("movie5_1", false);user.put("movie5_2", false);user.put("movie5_3", false);
            user.put("moviecheck", false);user.put("movie2check", false); user.put("movie3check", false);user.put("movie4check", false); user.put("movie5check", false);user.put("movie6check", false); user.put("movie7check", false);user.put("movie8check", false); user.put("movie9check", false);user.put("movie10check", false);
            user.put("movie11check", false);user.put("movie12check", false); user.put("movie13check", false);user.put("movie14check", false); user.put("movie15check", false);
            user.put("checkachi1", false);user.put("checkachi2", false);user.put("checkachi3", false);user.put("checkachi4", false);user.put("checkachi5", false);user.put("checkachi6", false);user.put("checkachi7", false);user.put("checkachi8", false);user.put("checkachi9", false);user.put("checkachi10", false);
            user.saveInBackground();

        }
    };
//選擇新增技能--------------------------------------------------------------------------------------
    public void swtskill(String x){
        switch (x){
            case "天眼":
                user.put("godeye", "1");
                break;
            case "開車":
                user.put("trick2", true);
                break;
            case "變裝":
                user.put("trick3", true);
                break;
            case "化學":
                user.put("trick5", true);
                break;
            case "搭訕":
                user.put("trick4", true);
                break;
            case "暗殺":
                user.put("trick1", true);
                break;
        }
    }
    //-------------------------------------------------------------------------------------------
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
