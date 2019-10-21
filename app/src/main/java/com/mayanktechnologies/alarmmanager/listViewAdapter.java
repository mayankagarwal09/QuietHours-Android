package com.mayanktechnologies.alarmmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class listViewAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList data;
    private static LayoutInflater inflater = null;

    public listViewAdapter(@NonNull Context context, ArrayList arrayList) {
        super(context, 0,arrayList);
        mContext=context;
        data=arrayList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {


        View v =view;
        if (view == null) {

            v = LayoutInflater.from(getContext()).inflate(R.layout.list_row,null);

            TextView title = (TextView) v.findViewById(R.id.slot_title);
            slots s = (slots) data.get(position);
            title.setText(s.getSlot_title());
        }
        return v;

    }
}
