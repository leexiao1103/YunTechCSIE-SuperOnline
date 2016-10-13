package com.example.user.yuncsie.itemlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.example.user.yuncsie.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/12/10.
 */
public class list1adapter extends BaseAdapter {
    private String[] drugsname = new String[]{"thanksdurgs", "sleepdrugs", "twodrugs", "getbigbig"};
    private ParseQuery<ParseObject> query = ParseQuery.getQuery("Drugs");
    private Activity activity;
    private List<String> mList;
    private HashMap<Integer, Boolean> isSelected;

    private static LayoutInflater inflater = null;

    public list1adapter(Activity a, List<String> list, HashMap<Integer,Boolean> isselect)
    {
        activity = a;
        mList = list;
        isSelected = isselect;
        /*Toast.makeText(activity,"adapter"+isselect.get(0).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(activity,"adapter"+isselect.get(1).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(activity,"adapter"+isselect.get(2).toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(activity,"adapter"+isselect.get(3).toString(),Toast.LENGTH_SHORT).show();*/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount()
    {
        return mList.size();
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if(convertView==null)
        {
            vi = inflater.inflate(R.layout.chekitemlist, null);
        }

        final CheckedTextView chkBshow = (CheckedTextView) vi.findViewById(R.id.check1);
        chkBshow.setText(mList.get(position).toString());
        /*query.getInBackground("QbrcTxZmMH", new GetCallback<ParseObject>() {
            public void done(ParseObject gameScore, ParseException e) {
                if (e == null) {
                   isSelected.put(position,chkBshow.isChecked());
                }
            }
        });*/

        chkBshow.setChecked(isSelected.get(position));

        return vi;
    }
}

