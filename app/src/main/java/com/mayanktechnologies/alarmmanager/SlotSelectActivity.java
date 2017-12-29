package com.mayanktechnologies.alarmmanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;

public class SlotSelectActivity extends AppCompatActivity implements SelectSlotsAdapter.SlotListViewItemClick{

    RecyclerView slot_select_recycler_view;
    SelectSlotsAdapter SlotSelectAdapter;
    Button addSlotsButton,clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_select);

        slot_select_recycler_view=(RecyclerView)findViewById(R.id.slot_select_recycler_view);
        SlotSelectAdapter=new SelectSlotsAdapter(Constants.slotsArrayList,this);
        GridLayoutManager manager=new GridLayoutManager(this,1);
        slot_select_recycler_view.setLayoutManager(manager);
        slot_select_recycler_view.setAdapter(SlotSelectAdapter);
        addSlotsButton=(Button)findViewById(R.id.add_slot_button);
        clearButton=(Button)findViewById(R.id.clear_button);
        addSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.removeAlarm(getApplicationContext());
                Constants.selectedSlots.clear();
                SlotSelectAdapter.notifyDataSetChanged();
            }
        });

        addSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onListItemClick(int index) {

    }

}
